
package com.shashank.phhub.producer.user;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import com.shashank.phhub.core.dto.GenericResponse;
import com.shashank.phhub.core.dto.MyData;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEmailProducer {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${email.rabbitmq.exchange}")
	private String exchange;

	@Value("${email.rabbitmq.routingkey}")
	private String routingkey;

	public UserEmailProducer() {

	}

	public void sendMessageToQueue(String msg) {

		rabbitTemplate.convertAndSend(exchange, routingkey,
				new MyData(msg));
		log.info("Sent msg = {}", msg);

	}

}
