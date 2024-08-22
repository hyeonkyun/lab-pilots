package kr.pe.hyeonkyun.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SensorDataConsumer {
	@KafkaListener(topics = "test_topic", groupId = "my-group")
	public void consume(String message) {
		System.out.println("Consumed message: " + message);
	}
}
