package com.bingo.rabbitMQ.controller;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bingo.rabbitMQ.model.User;

@RestController
@RequestMapping(value = "/rebbit")
public class RabbitmqController {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * convertSendAndReceive : </br>
	 * 使用 convertSendAndReceive
	 * 方法时的结果：使用此方法，只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间</br>
	 * convertAndSend : </br>
	 * 使用 convertAndSend 方法时的结果：输出时没有顺序，不需要等待，直接运行</br>
	 * 
	 * @return
	 */

	@RequestMapping(value = "/word")
	public String sendWord() {
		for (int i = 0; i < 5; i++) {
			rabbitTemplate.convertAndSend("queue_work", "测试work模型: " + i);
		}
		return "ok";
	}

	@RequestMapping(value = "/publish")
	public String sendPublish() {
		rabbitTemplate.convertAndSend("exchange_fanout", "", "测试发布订阅模型：", new CorrelationData("" + System.currentTimeMillis()));
		// for (int i = 0; i < 5; i++) {
		// rabbitTemplate.convertAndSend("exchange_fanout", "", "测试发布订阅模型：" + i,
		// new CorrelationData("" + System.currentTimeMillis()));
		// }
		return "ok";
	}

	@RequestMapping(value = "/topic")
	public String sendTopic() {
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				rabbitTemplate.convertSendAndReceive("exchange_topic", "topic.km.topic", "测试发布订阅模型：" + i);
			} else {
				rabbitTemplate.convertSendAndReceive("exchange_topic", "topic.km", "测试发布订阅模型：" + i);
			}
		}
		return "ok";
	}

	@RequestMapping(value = "/dlx")
	public String sendDlx() {
		rabbitTemplate.convertAndSend("exchange_dlx", "receive_key", "测试进入死信模型：", new CorrelationData("" + System.currentTimeMillis()));
		return "ok";
	}

	@RequestMapping(value = "/confirm")
	public String sendConfirm() {
		rabbitTemplate.convertAndSend("queue_confirm", new User(1, "kmq", "kmq123"), new CorrelationData("" + System.currentTimeMillis()));
		// rabbitTemplate.setConfirmCallback(confirmCallback);
		return "ok";
	}

	// 配置 confirm 机制
	// private final RabbitTemplate.ConfirmCallback confirmCallback = new
	// RabbitTemplate.ConfirmCallback() {
	// /**
	// * @param correlationData
	// * 消息相关的数据，一般用于获取 唯一标识 id
	// * @param ack
	// * true 消息确认成功，false 失败
	// * @param cause
	// * 确认失败的原因
	// */
	// @Override
	// public void confirm(CorrelationData correlationData, boolean ack, String
	// cause) {
	// if (ack) {
	// System.out.println("confirm 消息确认成功..." + correlationData.getId());
	// } else {
	// System.out.println("confirm 消息确认失败..." + correlationData.getId() + "
	// cause: " + cause);
	// }
	// }
	// };

	@RequestMapping(value = "/return")
	public String sendReturn() {
		// rabbitTemplate.convertAndSend("exchange_return", "return.km.km", new
		// User(1, "kmq", "kmq123"));
		rabbitTemplate.convertAndSend("exchange_return", "return.km", "测试 return 机制");
		return "ok";
	}
}
