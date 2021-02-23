package com.bingo.rabbitMQ.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	// 配置一个工作模型队列
	@Bean
	public Queue queueWork1() {
		return new Queue("queue_work");
	}

	/* =============================== */
	// 发布订阅模式
	// 声明两个队列
	@Bean
	public Queue queueFanout1() {
		return new Queue("queue_fanout1");
	}

	@Bean
	public Queue queueFanout2() {
		return new Queue("queue_fanout2");
	}

	// 准备一个交换机
	@Bean
	public FanoutExchange exchangeFanout() {
		return new FanoutExchange("exchange_fanout");
	}

	// 将交换机和队列进行绑定
	@Bean
	public Binding bindingExchange1() {
		return BindingBuilder.bind(queueFanout1()).to(exchangeFanout());
	}

	@Bean
	public Binding bindingExchange2() {
		return BindingBuilder.bind(queueFanout2()).to(exchangeFanout());
	}

	/* topic 模型 */
	@Bean
	public Queue queueTopic1() {
		return new Queue("queue_topic1");
	}

	@Bean
	public Queue queueTopic2() {
		return new Queue("queue_topic2");
	}

	@Bean
	public TopicExchange exchangeTopic() {
		return new TopicExchange("exchange_topic");
	}

	/**
	 * *表示只匹配一个词 #表示匹配多个词
	 * 
	 * @return
	 */
	@Bean
	public Binding bindingTopic1() {
		return BindingBuilder.bind(queueTopic1()).to(exchangeTopic()).with("topic.#");
	}

	@Bean
	public Binding bindingTopic2() {
		return BindingBuilder.bind(queueTopic2()).to(exchangeTopic()).with("topic.*");
	}

	// 测试confirm 机制，专门创建了一个队列
	@Bean
	public Queue queueConfirm() {
		return new Queue("queue_confirm");
	}

	// return机制
	@Bean
	public Queue queueReturn() {
		return new Queue("queue_return");
	}

	@Bean
	public TopicExchange exchangeReturn() {
		return new TopicExchange("exchange_return");
	}

	@Bean
	public Binding bindingReturn() {
		return BindingBuilder.bind(queueReturn()).to(exchangeReturn()).with("return.*");
	}

	// TTL 队列
	@Bean
	public Queue queueTTL() {
		Map<String, Object> map = new HashMap<>(1);
		map.put("x-message-ttl", 10000);
		return new Queue("queue_ttl", true, false, false, map);
	}

	// 产生死信的队列
	@Bean
	public Queue queueDLX() {
		Map<String, Object> map = new HashMap<>(2);
		// 5秒后，消息自动变为死信
		map.put("x-message-ttl", 5000);
		map.put("x-dead-letter-exchange", "exchange_receive");
		map.put("x-dead-letter-routing-key", "receive_key");
		return new Queue("queue_dlx", true, false, false, map);
	}

	// 死信交换机
	@Bean
	public DirectExchange exchangeDLX() {
		return new DirectExchange("exchange_dlx");
	}

	// 给死信队列绑定交换机
	@Bean
	public Binding bindingDLX() {
		return BindingBuilder.bind(queueDLX()).to(exchangeDLX()).with("receive_key");
	}

	// 死信接收交换机
	@Bean
	public DirectExchange exchangeReceive() {
		return new DirectExchange("exchange_receive");
	}

	// 接收死信的队列
	@Bean
	public Queue queueReceive() {
		return new Queue("queue_receive");
	}

	// 将交换机与队列绑定
	@Bean
	public Binding bindingReceive() {
		return BindingBuilder.bind(queueReceive()).to(exchangeReceive()).with("receive_key");
	}

	// 死信队列
	@Bean
	public Queue queueProdlx() {
		return new Queue("queue_prodlx");
	}

}
