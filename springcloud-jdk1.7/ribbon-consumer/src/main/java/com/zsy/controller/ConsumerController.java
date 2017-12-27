package com.zsy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
  @Autowired
  RestTemplate restTemplate;
  
  
  @RequestMapping("/ribbon-consumer")
  public String helloConsumer(String name) {
	  System.err.println("请求start");
	  Map<String, String> map = new HashMap<String,String>();
	  map.put("name",name);
	  String s = restTemplate.getForEntity("http://HELLO-SERVER/hello?name={name}", String.class,map).getBody();
	  System.out.println(s);
	  System.err.println("请求end");
	  return s;
  }
}
