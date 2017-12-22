package com.zsy.test.jm2;

import java.util.Map;

import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSATester {

    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            System.err.println("公钥: \n\r" + publicKey);
            System.err.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
       // test();
        testSign();
    }
    
    static void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "公钥加密1123gdhsfASDDD@#$%^&加密";
        System.out.println("\r加密前文字：\r\n" + source);
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

    static void testSign() throws Exception {
        System.err.println("\n\n\n\n\n\n");
        System.err.println("私钥加密——公钥解密");
        String source = "私钥RSA数字签名加密";
        System.out.println("原文字：\r\n" + source);
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
        String sign = RSAUtils.sign(source, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(source, privateKey, sign);
        System.err.println("验证结果:\r" + status);
    }

}