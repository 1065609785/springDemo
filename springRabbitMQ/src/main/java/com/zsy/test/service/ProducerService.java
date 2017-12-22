
 /**
 * Project Name:springRabbitMQ
 * File Name:ProducerService.java
 * Package Name:com.zsy.test.service
 * Date:2017年12月14日上午11:14:26
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
*/
/**
 * Project Name:springRabbitMQ
 * File Name:ProducerService.java
 * Package Name:com.zsy.test.service
 * Date:2017年12月14日上午11:14:26
 * Copyright (c) 2017, zhaoshouyun All Rights Reserved.
 *
 */

package com.zsy.test.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProducerService 
 * Function: TODO ADD FUNCTION. 
 * date: 2017年12月14日 上午11:14:26 
 * @author zhaoshouyun
 * @version 
 * @since JDK 1.7
 */
@Service
public class ProducerService {
	@Autowired
    private AmqpTemplate amqpTemplate;
    
    public void sendQueue(String exchange_key, String queue_key, Object object) {
        // convertAndSend 将Java对象转换为消息发送至匹配key的交换机中Exchange
        amqpTemplate.convertAndSend(exchange_key, queue_key, object);
    }
}

