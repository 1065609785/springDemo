package com.zsy.aes;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;




 /**
 * ClassName: AESOperator 
 * Function:  AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 * 一般是配合RSA进行动态加解密
 * date: 2018年2月5日 下午4:14:19 
 * @author zhaoshouyun
 * @version 
 * @since JDK 1.7
 */
public class AESOUtils {

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     * key是aes的一个加密源，也就是通过该key加密后，必须通过该key就进行解密；对数据不是特别敏感的，直接用静态的key即可；
     * 有些场景下数据异常敏感，那么这个key就要动态生成，结合RSA进行加密，，就能做到每次请求，key都不一样，，安全程度大大加强；
     * 当然key可以为32，但是要下载两个jdk的jar包，好像是美国那边没有发出来这个jar，具体的key百度下
     */
	private String sKey = "yjkcmsggsqrxygi5";
    /**
     * 偏移量，随便给一个就可以了，，不给也行
     */
    private String ivParameter = "uuwcrjlkwxsxvnra";//偏移量
    private static AESOUtils instance = null;

    private AESOUtils() {

    }

    public static AESOUtils getInstance() {
        if (instance == null)
            instance = new AESOUtils();
        return instance;
    }

    public static String Encrypt(String encData ,String secretKey,String vector) throws Exception {

        if(secretKey == null) {
            return null;
        }
        if(secretKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }


    // 加密
    public String encrypt(String sSrc) throws Exception {
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。	
    }

    // 解密
    public String decrypt(String sSrc){
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
        	ex.printStackTrace();
            return null;
        }
    }

    /**解密
     * @param sSrc 解密字符串
     * @param key key
     * @param ivs ivs
     * @return
     * @throws Exception
     */
    public String decrypt(String sSrc,String key,String ivs) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }

   
    /**
     * getRandomString:获取随机字符串
     * @author zhaoshouyun
     * @param length
     * @return
     * @since JDK 1.7
     */
    @SuppressWarnings("unused")
	private static String getRandomString(int length) { //length表示生成字符串的长度  
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
        Random random = new Random();     
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < length; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }     
        return sb.toString();     
     }     
    
    
    public static void main(String[] args) throws Exception {
    	//System.err.println(getRandomString(16));
        // 需要加密的字串
        String cSrc = "测试AES进行加密";
        System.err.println("机密前字符串："+cSrc);
        // 加密
        long lStart = System.currentTimeMillis();
        String enString = AESOUtils.getInstance().encrypt(cSrc);
        System.err.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
        String DeString = AESOUtils.getInstance().decrypt(enString);
        System.err.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }

}
