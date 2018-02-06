package com.zsy.rsa;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


 /**
 * ClassName: RSATest 
 * Function: RSA 加密
 * date: 2018年2月5日 下午3:59:01 
 * @author zhaoshouyun
 * @version 
 * @since JDK 1.7
 */
public class RSATest {

    static String publicKey;
    static String privateKey;
    //初始化密钥对
    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            System.out.println("公钥:" + publicKey+" \n");
            System.out.println("私钥" + privateKey+" \n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
       //test();
       test2();
    }
    
    /**
     * test:公钥进行加密  私钥进行解密
     * @author zhaoshouyun
     * @throws Exception
     * @since JDK 1.7
     */
    static void test() throws Exception {
        System.err.println("-------公钥进行加密——私钥进行解密");
        String source = "这是用公钥加密的测试数据！@#￥%……&*（）——+“：？》》《《ok";
        System.out.println("加密前文字：\r\n" + source);
        byte[] data = source.getBytes("UTF-8");
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);

        //BASE64转码
        String jm = new BASE64Encoder().encode(encodedData);
        System.out.println("加密后文字：\r\n" + jm);
        //BASE64解码
        byte[] jmbt = StringUtils.isEmpty(jm) ? null : new BASE64Decoder().decodeBuffer(jm);

        byte[] decodedData = RSAUtils.decryptByPrivateKey(jmbt,
                privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
    }

    /**
     * test2:私钥加密  公钥解密
     * @author zhaoshouyun
     * @throws Exception
     * @since JDK 1.7
     */
    static void test2() throws Exception {
        System.err.println("\n\n\n\n");
        System.err.println("私钥进行加密——公钥进行解密");
        String source = "这是私钥进行解密，公钥进行解密测试！@#￥%……&**（（（））————+++}{“：？》《ok";
        System.out.println("原文字" + source);
        byte[] data = source.getBytes("UTF-8");
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        
        //BASE64转码
        String jm = new BASE64Encoder().encode(encodedData);
        System.out.println("加密后文字：\r\n" + jm);
        //BASE64解码
        byte[] jmbt = StringUtils.isEmpty(jm) ? null : new BASE64Decoder().decodeBuffer(jm);
        byte[] decodedData = RSAUtils.decryptByPublicKey(jmbt,
                publicKey);

        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);//可以进行签名
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }

}