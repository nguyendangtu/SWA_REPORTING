package com.miu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, Double> kafkaTemplate;

    public void send(String topic, Double value){
        kafkaTemplate.send(topic, value);
    }
}
