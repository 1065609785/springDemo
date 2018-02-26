package com.zsy.upgradejms.comsumer;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class Receiver implements SessionAwareMessageListener<TextMessage> {
	@JmsListener(destination = "${queue.destination}")
	public void receive(String message) {
		System.err.println(Thread.currentThread().getName()+ "receive:" + message);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onMessage(TextMessage msg, Session arg1) throws JMSException {
		System.err.println("onMessage：" + msg.toString());
	}

}
