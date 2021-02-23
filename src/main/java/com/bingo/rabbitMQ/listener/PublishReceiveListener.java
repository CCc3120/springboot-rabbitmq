package com.bingo.rabbitMQ.listener;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class PublishReceiveListener {

	@RabbitListener(queues = "queue_fanout1")
	public void receiveMsg1(String msg, Channel channel, Message message) throws IOException {
		System.out.println("队列1接收到消息：" + msg);
		// throw new RuntimeException();
		// 成功确认
		// requeue=true,表示将消息重新放入到队列中，false：表示直接从队列中删除，此时和

		// basicAck(long deliveryTag, false)的效果一样
//		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		// 失败确认
//		channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//		channel.basicPublish(exchange, routingKey, mandatory, props, body);
		//拒绝消息
//		channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
	}

	@RabbitListener(queues = "queue_fanout2")
	public void receiveMsg2(String msg, Channel channel, Message message) throws IOException {
		System.out.println("队列2接收到消息：" + msg);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
	}
}
