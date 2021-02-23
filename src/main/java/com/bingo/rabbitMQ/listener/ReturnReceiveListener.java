package com.bingo.rabbitMQ.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReturnReceiveListener {

	@RabbitListener(queues = "queue_return")
	public void receiveMsg(String msg) {
		System.out.println("接收的消息为：" + msg);
	}
}
