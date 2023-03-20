package miu.edu.swa.pproject.report.service;

import miu.edu.swa.pproject.report.domain.NSIValue;
import miu.edu.swa.pproject.report.dto.NSIValueDto;

import java.util.Date;
import java.util.Set;

public interface NsiValueService {
    Set<NSIValueDto> getByTopicName(String topicName);

    Set<NSIValueDto> getByTimestamp(Date timestamp);

    Set<NSIValueDto> getByTopicNameAndTimestamp(String topicName, Date timestamp);

    NSIValue saveRecord(Double value, Long timestamp, String topicName);
}
