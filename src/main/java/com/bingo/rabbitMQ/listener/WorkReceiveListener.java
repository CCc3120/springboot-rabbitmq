package com.bingo.rabbitMQ.listener;


import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class WorkReceiveListener {

	@RabbitListener(queues = "queue_work")
	public void receiveMessage(String msg, Channel channel, Message message) throws Exception {
		// 只包含发送的消息
		System.out.println("1接收到消息：" + msg);
		// channel 通道信息
		// message 附加的参数信息
		// requeue=true,表示将消息重新放入到队列中，false：表示直接从队列中删除，此时和basicAck(long
		// deliveryTag, false)的效果一样
		// channel.basicAck(System.currentTimeMillis(), false);
		// channel.waitForConfirms();
	}

	@RabbitListener(queues = "queue_work")
	public void receiveMessage2(Object obj, Channel channel, Message message) throws IOException {
		// 包含所有的信息
		System.out.println("2接收到消息：" + obj.toString());

		// channel.basicAck(System.currentTimeMillis(), false);
	}
}
