package com.zsy.test.jm2;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * <p>  
 * RSA公钥/私钥/签名工具包  
 * </p>  
 * <p>  
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）  
 * </p>  
 * <p>  
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>  
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>  
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全  
 * </p>  
 *   
 * @author IceWee  
 * @date 2012-4-26  
 * @version 1.0  
 */
public class RSAUtils {

	private static final Logger log = LoggerFactory.getLogger(RSAUtils.class);
	
    /**  
     * 加密算法RSA  
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**  
     * 签名算法  
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**  
     * 获取公钥的key  
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**  
     * 获取私钥的key  
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**  
     * RSA最大加密明文大小  
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**  
     * RSA最大解密密文大小  
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    /**
     * 解密失败code
     */
    public static final String DECRYPT_FAIL_CODE = "1005";
    
    /**
     * 解密失败描述
     */
    public static final String DECRYPT_FAIL_DESCRIBE = "安全认证失败，请重新登录";

    /**  
     * <p>  
     * 生成密钥对(公钥和私钥)  
     * </p>  
     *   
     * @return  
     * @throws Exception  
     */
    public static Map<String, Object> genKeyPair() throws CustomizeException {
    	Map<String, Object> keyMap = new HashMap<String, Object>(2);
        KeyPairGenerator keyPairGen= null;
		try {
			keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);
		} catch (Exception e) {
			log.error("生成密钥对(公钥和私钥)出现错误", e);
			throw new CustomizeException(DECRYPT_FAIL_CODE,DECRYPT_FAIL_DESCRIBE);
		}
        return keyMap;
    }

    /**  
     * <p>  
     * 用私钥对信息生成数字签名  
     * </p>  
     *   
     * @param data  
     *            已加密数据  
     * @param privateKey  
     *            私钥(BASE64编码)  
     *   
     * @return  
     * @throws Exception  
     */
    /*public static String sign(byte[] data, String privateKey) throws CustomizeException {
    	try {
	        byte[] keyBytes = Base64Utils.decode(privateKey);
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
	        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
	        signature.initSign(privateK);
	        signature.update(data);
	        return Base64Utils.encode(signature.sign());
	    	} catch (Exception e) {
	    		// TODO Auto-generated catch block
				log.error("用私钥对信息生成数字签名 错误", e);
				e.printStackTrace();
				throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }*/
    /**生成签名信息
     * @param data
     * @param privateKey
     * @return
     * @throws CustomizeException
     */
    public static String sign(String content, String sKeyStr) throws CustomizeException {
    	try {
    		   byte[] data = (content+sKeyStr).getBytes("UTF-8");
               String jm  = Base64Utils.encode(data);
               String result = MD5Util.MD5(jm.replaceAll("\\n",""));//RSAUtils.sign(encodedData,privateKey);
               return result;
	    	} catch (Exception e) {
	    		// TODO Auto-generated catch block
				log.error("用私钥对信息生成数字签名 错误", e);
				throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }

    /**  
     * <p>  
     * 校验数字签名  
     * </p>  
     *   
     * @param data  
     *            已加密数据  
     * @param publicKey  
     *            公钥(BASE64编码)  
     * @param sign  
     *            数字签名  
     *   
     * @return  
     * @throws Exception  
     *   
     */
   /* public static boolean verify(byte[] data, String publicKey, String sign)
            throws CustomizeException {
    	try {
	        byte[] keyBytes = Base64Utils.decode(publicKey);
	        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        PublicKey publicK = keyFactory.generatePublic(keySpec);
	        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
	        signature.initVerify(publicK);
	        signature.update(data);
	        return signature.verify(Base64Utils.decode(sign));
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
			log.error("校验数字签名错误 ", e);
			e.printStackTrace();
			throw new CustomizeException(DECRYPT_FAIL_CODE,DECRYPT_FAIL_DESCRIBE);
		}
    }*/
    
    public static boolean verify(String content, String sKeyStr, String sign)
            throws CustomizeException {
    	try {
 		   byte[] data = (content+sKeyStr).getBytes("UTF-8");
            String jm  = Base64Utils.encode(data);
            String result = MD5Util.MD5(jm.replaceAll("\\n",""));//RSAUtils.sign(encodedData,privateKey);
            return (result.equals(sign));
	    	} catch (Exception e) {
	    		// TODO Auto-generated catch block
				log.error("校验数字签名 错误", e);
				throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }

    /**  
     * <P>  
     * 私钥解密  
     * </p>  
     *   
     * @param encryptedData  
     *            已加密数据  
     * @param privateKey  
     *            私钥(BASE64编码)  
     * @return  
     * @throws Exception  
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData,
            String privateKey) throws CustomizeException {
    	try {
	        byte[] keyBytes = Base64Utils.decode(privateKey);
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	        cipher.init(Cipher.DECRYPT_MODE, privateK);
	        int inputLen = encryptedData.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段解密  
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	                cache = cipher
	                        .doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	            } else {
	                cache = cipher
	                        .doFinal(encryptedData, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_DECRYPT_BLOCK;
	        }
	        byte[] decryptedData = out.toByteArray();
	        out.close();
	        return decryptedData;
    	} catch (Exception e) {
    		String msg= "私钥解密发生异常";
    		log.error(msg, e);
			throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }

    /**  
     * <p>  
     * 公钥解密  
     * </p>  
     *   
     * @param encryptedData  
     *            已加密数据  
     * @param publicKey  
     *            公钥(BASE64编码)  
     * @return  
     * @throws Exception  
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData,
            String publicKey) throws CustomizeException {
    	try {
	        byte[] keyBytes = Base64Utils.decode(publicKey);
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        Key publicK = keyFactory.generatePublic(x509KeySpec);
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	        cipher.init(Cipher.DECRYPT_MODE, publicK);
	        int inputLen = encryptedData.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段解密  
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
	                cache = cipher
	                        .doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
	            } else {
	                cache = cipher
	                        .doFinal(encryptedData, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_DECRYPT_BLOCK;
	        }
	        byte[] decryptedData = out.toByteArray();
	        out.close();
	        return decryptedData;
    	} catch (Exception e) {
    		String msg= "公钥解密发生异常";
    		log.error(msg, e);
    		throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }

    /**  
     * <p>  
     * 公钥加密  
     * </p>  
     *   
     * @param data  
     *            源数据  
     * @param publicKey  
     *            公钥(BASE64编码)  
     * @return  
     * @throws Exception  
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws CustomizeException {
    	try {
		
	        byte[] keyBytes = Base64Utils.decode(publicKey);
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        Key publicK = keyFactory.generatePublic(x509KeySpec);
	        // 对数据加密  
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	        cipher.init(Cipher.ENCRYPT_MODE, publicK);
	        int inputLen = data.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段加密  
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
	                cache = cipher.doFinal(data);
	            } else {
	                cache = cipher.doFinal(data);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_ENCRYPT_BLOCK;
	        }
	        byte[] encryptedData = out.toByteArray();
	        out.close();
	        return encryptedData;
    	} catch (Exception e) {
    		String msg= "公钥加密发生异常";
    		log.error(msg, e);
    		throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }

    /**  
     * <p>  
     * 私钥加密  
     * </p>  
     *   
     * @param data  
     *            源数据  
     * @param privateKey  
     *            私钥(BASE64编码)  
     * @return  
     * @throws Exception  
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws CustomizeException {
    	try {
	        byte[] keyBytes = Base64Utils.decode(privateKey);
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	        cipher.init(Cipher.ENCRYPT_MODE, privateK);
	        int inputLen = data.length;
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] cache;
	        int i = 0;
	        // 对数据分段加密  
	        while (inputLen - offSet > 0) {
	            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
	                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
	            } else {
	                cache = cipher.doFinal(data, offSet, inputLen - offSet);
	            }
	            out.write(cache, 0, cache.length);
	            i++;
	            offSet = i * MAX_ENCRYPT_BLOCK;
	        }
	        byte[] encryptedData = out.toByteArray();
	        out.close();
	        return encryptedData;
    	} catch (Exception e) {
    		String msg= "私钥加密发生异常";
    		log.error(msg, e);
    		throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }

    /**  
     * <p>  
     * 获取私钥  
     * </p>  
     *   
     * @param keyMap  
     *            密钥对  
     * @return  
     * @throws Exception  
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws CustomizeException {
    	try {
	        Key key = (Key) keyMap.get(PRIVATE_KEY);
	        return Base64Utils.encode(key.getEncoded());
    	} catch (Exception e) {
    		String msg= "获取私钥 发生异常";
    		log.error(msg, e);
    		throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }

    /**  
     * <p>  
     * 获取公钥  
     * </p>  
     *   
     * @param keyMap  
     *            密钥对  
     * @return  
     * @throws Exception  
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws CustomizeException {
    	try {
	        Key key = (Key) keyMap.get(PUBLIC_KEY);
	        return Base64Utils.encode(key.getEncoded());
    	} catch (Exception e) {
    		String msg= "获取公钥发生异常";
    		log.error(msg, e);
    		throw new CustomizeException(DECRYPT_FAIL_CODE, DECRYPT_FAIL_DESCRIBE);
		}
    }

}