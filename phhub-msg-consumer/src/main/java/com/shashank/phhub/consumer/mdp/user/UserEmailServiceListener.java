package com.shashank.phhub.consumer.mdp.user;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.shashank.phhub.core.dto.MyData;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserEmailServiceListener {

	@RabbitListener(queues = "${email.rabbitmq.queue}" )
	public void receiveMessage(MyData message) {
		log.info("Received <" + message + ">");
		log.info("Message processed and received :: {}", message.getMessage());
	}
}
