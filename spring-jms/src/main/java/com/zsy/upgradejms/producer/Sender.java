package com.zsy.upgradejms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class Sender {
	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(final String destination, final String message) {
		this.jmsTemplate.convertAndSend(destination, message);
	}
}
