package com.zsy.defaultjms.config;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsBean {

	@Bean
	public Destination destination() {
		return new ActiveMQQueue("mytest.queue");
	}
}
