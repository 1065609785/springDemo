
 /**
 * Project Name:springRabbitMQ
 * File Name:ConsumerServiceTest.java
 * Package Name:com.zsy.test.service
 * Date:2017年12月14日下午2:07:42
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
*/
/**
 * Project Name:springRabbitMQ
 * File Name:ConsumerServiceTest.java
 * Package Name:com.zsy.test.service
 * Date:2017年12月14日下午2:07:42
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
 */

package com.zsy.test.service;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * ClassName: ConsumerServiceTest 
 * Function: TODO ADD FUNCTION. 
 * date: 2017年12月14日 下午2:07:42 
 * @author zhaoshouyun
 * @version 
 * @since JDK 1.7
 */
@Service
public class ConsumerServiceTest {
	 public void getMessage(Map<String, Object> message) {
	        System.out.println("消费者：" + message);
	    }
	 public void getMessage1(Map<String, Object> message) {
		 System.out.println("消费者1：" + message);
	 }
}

