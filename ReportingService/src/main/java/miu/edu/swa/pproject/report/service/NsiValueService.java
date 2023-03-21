package miu.edu.swa.pproject.report.service;

import miu.edu.swa.pproject.report.domain.NSIValue;
import miu.edu.swa.pproject.report.dto.NsiReportDto;

import java.util.Set;

public interface NsiValueService {
    NsiReportDto getByTopicName(String topicName);

    Set<NsiReportDto> getByDuration(Long from, Long to);

    NsiReportDto getByTopicNameAndDuration(String topicName, Long from, Long to);

    NSIValue save(Double value, Long timestamp, String topicName);
}
