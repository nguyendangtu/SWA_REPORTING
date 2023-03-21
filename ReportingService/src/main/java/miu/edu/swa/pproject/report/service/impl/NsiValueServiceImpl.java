package miu.edu.swa.pproject.report.service.impl;

import miu.edu.swa.pproject.report.domain.KafkaTopic;
import miu.edu.swa.pproject.report.domain.NSIValue;
import miu.edu.swa.pproject.report.dto.NsiReportDto;
import miu.edu.swa.pproject.report.dto.NsiValueDto;
import miu.edu.swa.pproject.report.repository.KafkaTopicRepository;
import miu.edu.swa.pproject.report.repository.NsiValueRepository;
import miu.edu.swa.pproject.report.service.NsiValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NsiValueServiceImpl implements NsiValueService {

    public static final String TOPIC_NAME_SEPARATOR = "_";
    private final NsiValueRepository nsiValueRepository;
    private final KafkaTopicRepository kafkaTopicRepository;

    Comparator<NsiValueDto> timestampComparator = (a, b) -> b.getTimestamp().compareTo(a.getTimestamp());
    @Autowired
    public NsiValueServiceImpl(NsiValueRepository nsiValueRepository, KafkaTopicRepository kafkaTopicRepository) {
        this.nsiValueRepository = nsiValueRepository;
        this.kafkaTopicRepository = kafkaTopicRepository;
    }

    @Override
    public NsiReportDto getByTopicName(String topicName) {
        return kafkaTopicRepository.findById(topicName).map(kafkaTopic -> {
            Set<NSIValue> values = nsiValueRepository.findByTopic(kafkaTopic);
            NsiReportDto report = new NsiReportDto();
            report.setTopicName(kafkaTopic.getName());
            report.setData(values.stream().map(v -> new NsiValueDto(v.getId(), v.getValue(), v.getTimestamp())).sorted(timestampComparator).collect(Collectors.toCollection(LinkedHashSet::new)));
            return report;
        }).orElseGet(NsiReportDto::new);
    }

    @Override
    public Set<NsiReportDto> getByDuration(Long from, Long to) {
        Set<NSIValue> values = nsiValueRepository.findByTimestampBetween(from, to);
        return values.stream()
                .map(v -> new NsiValueDto(v.getId(), v.getValue(), v.getTimestamp(), v.getTopic().getName()))
                .collect(Collectors.groupingBy(NsiValueDto::getTopic, Collectors.toSet()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .map(e -> new NsiReportDto(e.getKey(), e.getValue().stream().sorted(timestampComparator).collect(Collectors.toCollection(LinkedHashSet::new))))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public NsiReportDto getByTopicNameAndDuration(String topicName, Long from, Long to) {
        return kafkaTopicRepository.findById(topicName).map(kafkaTopic -> {
            Set<NSIValue> values = nsiValueRepository.findByTopicNameAndTimestampBetween(topicName, from, to);
            NsiReportDto report = new NsiReportDto();
            report.setTopicName(kafkaTopic.getName());
            report.setData(values.stream().map(v -> new NsiValueDto(v.getId(), v.getValue(), v.getTimestamp())).sorted(timestampComparator).collect(Collectors.toCollection(LinkedHashSet::new)));
            return report;
        }).orElseGet(NsiReportDto::new);
    }

    @Override
    public NSIValue save(Double value, Long timestamp, String topicName) {
        final KafkaTopic kafkaTopic;
        Optional<KafkaTopic> opKafkaTopicById = kafkaTopicRepository.findById(topicName);
        if (opKafkaTopicById.isPresent()) {
            kafkaTopic = opKafkaTopicById.get();
        } else {
            int fistIndex = 0;
            int secondIndex = 0;
            String[] parts = topicName.split(TOPIC_NAME_SEPARATOR);
            if (parts.length == 3) {
                fistIndex = Integer.parseInt(parts[1]);
                secondIndex = Integer.parseInt(parts[2]);
            }
            kafkaTopic = kafkaTopicRepository.save(new KafkaTopic(topicName, fistIndex, secondIndex));
        }

        NSIValue nsiValue = new NSIValue();
        nsiValue.setValue(value);
        nsiValue.setTimestamp(timestamp);
        nsiValue.setTopic(kafkaTopic);
        return nsiValueRepository.save(nsiValue);
    }
}
