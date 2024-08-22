package kr.pe.hyeonkyun.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatahubProducerApplication implements CommandLineRunner {

	@Autowired
	private DatahubProducerListener datahubProducerListener;

	public DatahubProducerApplication(DatahubProducerListener _datahubProducerListener) {
		this.datahubProducerListener = _datahubProducerListener;
	}

	public static void main(String[] args) {
		SpringApplication.run(DatahubProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		datahubProducerListener.startThread();
	}
}
