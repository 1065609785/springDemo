package com.zsy.defaultjms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zsy.defaultjms.config.JmsBean;
import com.zsy.defaultjms.producer.Producer;

@RestController
public class TestController {
	@Autowired
	Producer producer;

	@Autowired
	JmsBean bean;

	@RequestMapping("/test")
	public String test(String name, int a) {
		if (a == 0) {
			a = 10;
		}
		for (int i = 0; i < a; i++) {
			//producer.sendMessage(bean.destination(), "my name is " + name + i);
		}

		return "SUCCESS";

	}

}
