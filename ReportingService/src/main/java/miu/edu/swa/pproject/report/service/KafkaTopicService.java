package miu.edu.swa.pproject.report.service;

import java.util.Set;

public interface KafkaTopicService {
    Set<String> getAllTopics();

    void saveTopic(String topic);
}
