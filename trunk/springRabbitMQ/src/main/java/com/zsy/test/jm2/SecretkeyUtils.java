
 /**
 * Project Name:xnw-dubbo-consumer
 * File Name:SecretkeyUtils.java
 * Package Name:com.aisino.dubbo.framework.utils.encryption
 * Date:2017年9月15日下午1:33:46
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
*/

package com.zsy.test.jm2;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SecretkeyUtils <>
 * Function: 获取秘钥
 * date: 2017年9月15日 下午1:34:24 <>
 * @author zhaoshouyun
 * @version [版本号, 2017年9月15日]
 * @see  [相关类/方法] 
 * @since  [产品/模块版本]  JDK 1.7
 */
public class SecretkeyUtils {
	
  /**
 * getSecretKeys:获取秘钥
 * @author zhaoshouyun
 * @return
 * @see [类、类#方法、类#成员]
 */
public  static Map<String, String> getSecretKeys(){
	  Map<String, String> resultMap = new HashMap<String,String>();
	  try {
		  Map<String, Object> keyMap = RSAUtils.genKeyPair();
		  //公钥
		  String publicKey = RSAUtils.getPublicKey(keyMap);
		  String privateKey = RSAUtils.getPrivateKey(keyMap);
		  resultMap.put("publicKey", publicKey);
		  //私钥
		  resultMap.put("privateKey", privateKey);
	  } catch (Exception e) {
		  e.printStackTrace();
		}
	  return resultMap;
  }
}

