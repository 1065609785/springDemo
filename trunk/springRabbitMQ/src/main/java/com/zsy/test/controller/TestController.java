
 /**
 * Project Name:springRabbitMQ
 * File Name:TestController.java
 * Package Name:com.zsy.test.Controller
 * Date:2017年12月14日上午11:11:23
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
*/
/**
 * Project Name:springRabbitMQ
 * File Name:TestController.java
 * Package Name:com.zsy.test.Controller
 * Date:2017年12月14日上午11:11:23
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
 */

package com.zsy.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsy.test.service.ProducerService;
import com.zsy.websocket.MyMessageHandler;

/**
 * ClassName: TestController 
 * Function: TODO ADD FUNCTION. 
 * date: 2017年12月14日 上午11:11:23 
 * @author zhaoshouyun
 * @version 
 * @since JDK 1.7
 */
@Controller
public class TestController {

	@Autowired
	ProducerService producerService;
	
	@Autowired
	MyMessageHandler handler;
	
	@Value("${mq.queue}")
	private String queueId;
	
	@RequestMapping("/sendMsg")
	public String sendMsg(){
		 Map<String, Object> map = new HashMap<String, Object>();
	        map.put("data", "hello rabbitmq111");
	        System.err.println("111111111111111111111111111");
	        // 注意：第二个属性是 Queue 与 交换机绑定的路由
	        producerService.sendQueue(queueId + "_exchange", queueId + "_patt", map);
	        //map.put("data", "hello rabbitmq222");
	        producerService.sendQueue(queueId + "_exchange", "zsyQueue" + "_patt", "哈哈哈哈");
	        return "success";
	}
	
	
	@RequestMapping("/get")
	public String  get(){
		//handler.sendMessageToUser("101", "服务端发送的内容");
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/get1")
	public String  send(String name){
		handler.sendMessageToUser("zhaoshouyun", "服务端发送的内容:"+name);
		return "success";
	}
	
	
}

