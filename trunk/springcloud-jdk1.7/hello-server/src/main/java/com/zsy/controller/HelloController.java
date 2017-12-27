package com.zsy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	private final Logger logger = Logger.getLogger(HelloController.class);
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping("/hello")
	public void index(HttpServletResponse response,String name) {
		
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello,host="+instance.getHost()+",port="+instance.getPort()+",service_id="+instance.getServiceId());
		
		System.err.println("hello word"+name);
		try {
			response.getWriter().write("hello world"+name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
