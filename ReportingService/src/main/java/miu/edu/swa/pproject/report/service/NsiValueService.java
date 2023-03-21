package miu.edu.swa.pproject.report.service;

import miu.edu.swa.pproject.report.domain.NSIValue;

import java.util.Set;

public interface NsiValueService {
    Set<NSIValue> getByTopicName(String topicName);

    Set<NSIValue> getByDuration(Long from, Long to);

    Set<NSIValue> getByTopicNameAndDuration(String topicName, Long from, Long to);

    NSIValue save(Double value, Long timestamp, String topicName);
}
