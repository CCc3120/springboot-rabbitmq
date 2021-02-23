package com.bingo.rabbitMQ.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiveListener {

	@RabbitListener(queues = "queue_topic1")
	public void receiveMsg1(String msg) {
		System.out.println("消费者1接收到：" + msg);
	}

	@RabbitListener(queues = "queue_topic2")
	public void receiveMsg2(String msg) {
		System.out.println("消费者2接收到：" + msg);
	}
}
