package com.miu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Nsi1 {

	@Autowired
	private Sender sender;

	@Value("${app.topic.nsi_prefix}")
	private String nsiPrefix;

//	@Value("${app.generator.min}")
//	private String min;
//
//	@Value("${app.generator.max}")
//	private String max;

	@Scheduled(fixedRate = 2000)
	public void nsi1() {

		double min = 0;
		double max = 20;
		double value = SimulatorApp.round(min + Math.random() * (max - min), 2);

		System.out.println("Generating number !!!" + value );
		sender.send(nsiPrefix+"_1_2", value);
	}

	
}
