package com.zsy.test.jm2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AESUtils {
	private static final Logger log = LoggerFactory.getLogger(AESUtils.class);
	 /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    //private String sKey = "smkldospdosldaaa";//key，可自行修改
    private static String sKey = getRandomString(16);//key，可自行修改
    private String ivParameter = "0392039203920300";//偏移量,可自行修改
    private static AESUtils instance = null;
    /**
     * 加密后的内容
     */
    public static String encryptStr= "encryptStr";
    
    /**加密用的 key
     * 
     */
    public static String sKeyStr= "sKey";

    private AESUtils() {

    }

    public static AESUtils getInstance() {
        if (instance == null)
            instance = new AESUtils();
        sKey = getRandomString(16);
        return instance;
    }

    // 加密
    public Map<String, String> encrypt(String sSrc) throws CustomizeException {
       try {
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        String encryptStr = Base64Utils.encode(encrypted);// 此处使用BASE64做转码。
        Map<String, String> map = new HashMap<String, String>(3);
        map.put(AESUtils.encryptStr, encryptStr);
        map.put(AESUtils.sKeyStr, sKey);
        return map;
       } catch (Exception e) {
    	   log.error("aes加密错误出现错误参数:"+sSrc);
    	   log.error("aes加密错误出现错误Exception:",e);
    	   e.printStackTrace();
    	   throw new CustomizeException(RSAUtils.DECRYPT_FAIL_CODE, RSAUtils.DECRYPT_FAIL_DESCRIBE);
       }
    }

    //解密
    public String decrypt(String sSrc,String sKeyStr) throws CustomizeException{
        try {
            byte[] raw = sKeyStr.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64Utils.decode(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
        	log.error("aes解密错误出现错误参数:"+sSrc);
     	   log.error("aes解密错误出现错误Exception:",ex);
     	   ex.printStackTrace();
     	   throw new CustomizeException(RSAUtils.DECRYPT_FAIL_CODE, RSAUtils.DECRYPT_FAIL_DESCRIBE);
        }
    }
    /**
     * @param length
     * @return
     */
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

  /*  public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "[{\"request_no\":\"1001\",\"service_code\":\"FS0001\",\"contract_id\":\"100002\",\"order_id\":\"0\",\"phone_id\":\"13913996922\",\"plat_offer_id\":\"100094\",\"channel_id\":\"1\",\"activity_id\":\"100045\"}]";

        // 加密
        long lStart = System.currentTimeMillis();
        String enString = AESOperator.getInstance().encrypt(cSrc);
        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
        String DeString = AESOperator.getInstance().decrypt(enString);
        System.out.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }*/

}
