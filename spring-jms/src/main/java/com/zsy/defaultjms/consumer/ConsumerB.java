package com.zsy.defaultjms.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class ConsumerB {
	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	/*@JmsListener(destination = "mytest.queue")
	@SendTo("out.queue") // 该注解是将处理过的值放回到队列里
	public String receiveQueue(String text) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = "ConsumerB收到的报文为:" + text;
		System.out.println(s);
		return "return msg : " + s;

	}*/
}
