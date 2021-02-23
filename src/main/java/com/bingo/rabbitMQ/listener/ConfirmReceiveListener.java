package com.bingo.rabbitMQ.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.bingo.rabbitMQ.model.User;

@Component
public class ConfirmReceiveListener {

	static int a = 0;

	@RabbitListener(queues = "queue_confirm")
	public void receiveMsg(User user) {
		System.out.println("接收到的消息为：" + user);
//		a++;
//		if (a % 2 == 0) {
//			System.out.println("接收到的消息为：" + user);
//		}else {
//			System.out.println(1 / 0);
//		}
		
		
	}
}
