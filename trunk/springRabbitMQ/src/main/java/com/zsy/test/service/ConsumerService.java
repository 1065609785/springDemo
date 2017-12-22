
 /**
 * Project Name:springRabbitMQ
 * File Name:ConsumerService.java
 * Package Name:com.zsy.test.service
 * Date:2017年12月14日上午11:16:09
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
*/
/**
 * Project Name:springRabbitMQ
 * File Name:ConsumerService.java
 * Package Name:com.zsy.test.service
 * Date:2017年12月14日上午11:16:09
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
 */

package com.zsy.test.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * ClassName: ConsumerService 
 * Function: TODO ADD FUNCTION. 
 * date: 2017年12月14日 上午11:16:09 
 * @author zhaoshouyun
 * @version 
 * @since JDK 1.7
 */
@Service
@Component
public class ConsumerService  implements MessageListener{

	@Override
	public void onMessage(Message message) {
			System.out.println("消息消费者 = " +message.toString());
    }

}

