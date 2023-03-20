package com.miu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class SimulatorApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SimulatorApp.class, args);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private Sender sender;

	@Value("${app.generator.min}")
	private String minStr;

	@Value("${app.generator.max}")
	private String maxStr;

    @Value("${app.msg.topic.prefix}")
    private String topicPrefix;

    @Value("${app.msg.topic.list}")
    private String[] topicList;

    @Value("${app.msg.interval.list}")
    private String[] intervalList;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Receiver is running and waiting for messages");
        double min = Double.parseDouble(minStr);
		double max = Double.parseDouble(maxStr);

        for(int i=0; i<topicList.length;i++){
            RunnableTask task = new RunnableTask(sender, topicPrefix + topicList[i], min, max);
            taskScheduler.scheduleWithFixedDelay(task,Long.parseLong(intervalList[i]));
        }
    }



}