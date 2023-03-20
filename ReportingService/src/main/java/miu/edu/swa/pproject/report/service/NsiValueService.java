package miu.edu.swa.pproject.report.service;

import miu.edu.swa.pproject.report.domain.NSIValue;

import java.util.Date;
import java.util.Set;

public interface NsiValueService {
    Set<NSIValue> getByTopicName(String topicName);

    Set<NSIValue> getByTimestamp(Date timestamp);

    Set<NSIValue> getByTopicNameAndTimestamp(String topicName, Date timestamp);

    NSIValue saveRecord(Double value, Long timestamp, String topicName);
}
