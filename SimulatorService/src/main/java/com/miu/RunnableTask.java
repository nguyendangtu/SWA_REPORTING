package com.miu;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

class RunnableTask implements Runnable{
    private Sender sender;
    private String topic;
    private Double min;
    private Double max;

    @Value("${app.generator.min}")
    private String minStr;

    @Value("${app.generator.max}")
    private String maxStr;
    public RunnableTask(Sender sender, String topic, Double min, Double max){
        this.sender = sender;
        this.topic = topic;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {

        double value = SimulatorApp.round(min + Math.random() * (max - min), 2);

        System.out.println("Generating number !!!" + value );
        sender.send(topic, value);
    }
}
