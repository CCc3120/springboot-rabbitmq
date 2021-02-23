package com.bingo.rabbitMQ.listener;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class DlxReceiveListener {

	@RabbitListener(queues = "queue_prodlx")
	public void receiveMsg(String msg, Channel channel, Message message) throws IOException {
		System.out.println("队列接收到消息：" + msg);

//		channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
	}
}
