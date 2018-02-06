
 
/**
 * Project Name:AES_RSA
 * File Name:AesRSATest.java
 * Package Name:com.zsy.test
 * Date:2018年2月5日下午4:26:43
 * Copyright (c) 2018, zhaoshouyun All Rights Reserved.
 *
 */

package com.zsy.test;


 /**
 * ClassName: AesRSATest 
 * Function: TODO ADD FUNCTION. 
 * date: 2018年2月5日 下午4:26:43 
 * @author zhaoshouyun
 * @version 
 * @since JDK 1.7
 */
public class AesRSATest {

	/**
	 * 在进行AES+RSA进行联合加密时，有必要把过程说下（用字母代替没步操作的结果）：
	 * 1、模拟服务端和客户端通信场景；客户端在登陆成功后，服务端生成RSA公钥和私钥，把公钥返回客户端，私钥服务端保存起来（如果服务端有状态直接放入session即可，如果无状态，放入redis之类的缓存里，然后把redisKey也返回客户端）
	 * 2、客户端拿到公钥后也要保存起来；这是客户端和服务端进行加解密的必须要拿到公钥和私钥，同时要保存对应 ，否则无法进行加解密
	 * 3、客户端第一步：随机生成AES所需要的16位key得到 A；AES用A进行将参数进行加密得到B
	 * 4、客户端第二步：用公钥进行A加密等到C
	 * 5、客户端第三步：把B和C （如果有redisKey也要传到服务端）参数传给服务端对应的接口
	 * 6、服务端第一步：收到客户端发来的请求，根据session或者redisKey获取私钥得到D
	 * 7、服务端第二步：用私钥进行解密C等到A
	 * 8、服务端第三步：用AES所需要的A进行解密B，得到请求参数E；
	 * 9、到这一步客户端请求服务端进行加解密完成，服务获取解密后参数进行服务端业务处理得到结果返回给客户端过程，其实反过来即可
	 * 10、服务端第一步：随机生成AES所需要的16位key得到 F；AES用F进行将结果进行加密得到G
	 * 11、服务端第二步：用私钥进行F加密等到H
	 * 12、服务端第三步：把H和G返回给客户端
	 * 6、服务端第一步：收到服务端响应接口求，把客户端保存的公钥给获取出来（通过session或者其他的）
	 * 7、服务端第二步：用公钥进行解密H等到I
	 * 8、服务端第三步：用AES所需要的I进行解密G，得到结果J；
	 * ok整个流程这样，，其实也没什么，，
	 *
	 */
	public static void main(String[] args) {
      //这里代码不写了，，只要把AES包代码看懂，测试下，，再把RSA测试下，根据我上面的步骤即可，，，，，有问题可以联系我，，发我邮箱1065609785@qq.com

	}

}

