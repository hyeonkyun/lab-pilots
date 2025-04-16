package kr.pe.hyeonkyun.producer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.hyeonkyun.producer.service.SensorDataProducer;
 
@RestController
public class PilotController {
	@Autowired
	private SensorDataProducer sensorDataProducer;

	@GetMapping("/publish")
	public String sendMessageToKafka(@RequestParam("message") String message) {
		sensorDataProducer.sendMessage(message);
		return "Message sent to Kafka topic: " + message;
	}
}
