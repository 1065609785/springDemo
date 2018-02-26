package com.zsy.upgradejms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zsy.upgradejms.producer.Sender;

@RestController
public class TestControllerUp {
	@Autowired
	private Sender sender;

	@Value("${queue.destination}")
	private String destination;

	@RequestMapping(value = "/say/{msg}/to/{name}", method = RequestMethod.GET)
	public Map<String, Object> say(@PathVariable String msg, @PathVariable String name) {
		Map<String, Object> map = new HashMap<>();
		map.put("msg", msg);
		map.put("name", name);
        for (int i = 0; i < 50; i++) {
			
        	sender.send(destination, msg+i);
		}

		return map;
	}
}
