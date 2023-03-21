package miu.edu.swa.pproject.report.service.impl;

import miu.edu.swa.pproject.report.domain.KafkaTopic;
import miu.edu.swa.pproject.report.repository.KafkaTopicRepository;
import miu.edu.swa.pproject.report.service.KafkaTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KafkaTopicServiceImpl implements KafkaTopicService {
    private final KafkaTopicRepository kafkaTopicRepository;

    @Autowired
    public KafkaTopicServiceImpl(KafkaTopicRepository kafkaTopicRepository) {
        this.kafkaTopicRepository = kafkaTopicRepository;
    }

    @Override
    public Set<String> getAllTopics() {
        return kafkaTopicRepository.findAll().stream().map(KafkaTopic::getName).collect(Collectors.toSet());
    }

    @Override
    public void saveTopic(String topic) {
        kafkaTopicRepository.findById(topic).ifPresentOrElse(kafkaTopic -> {
        }, () -> kafkaTopicRepository.save(new KafkaTopic(topic)));
    }
}
