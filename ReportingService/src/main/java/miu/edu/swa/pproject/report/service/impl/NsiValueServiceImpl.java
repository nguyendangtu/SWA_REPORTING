package miu.edu.swa.pproject.report.service.impl;

import miu.edu.swa.pproject.report.converter.NsiValueConverter;
import miu.edu.swa.pproject.report.dto.NSIValueDto;
import miu.edu.swa.pproject.report.repository.NsiValueRepository;
import miu.edu.swa.pproject.report.service.NsiValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NsiValueServiceImpl implements NsiValueService {

    private NsiValueRepository nsiValueRepository;

    private NsiValueConverter nsiValueConverter;

    @Autowired
    public NsiValueServiceImpl(NsiValueRepository nsiValueRepository, NsiValueConverter nsiValueConverter) {
        this.nsiValueRepository = nsiValueRepository;
        this.nsiValueConverter = nsiValueConverter;
    }

    @Override
    public Set<NSIValueDto> getByTopicName(String topicName) {
        return nsiValueRepository.findByTopicName(topicName)
                .stream()
                .map(nsiValueConverter::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<NSIValueDto> getByTimestamp(Date timestamp) {
        return nsiValueRepository.findByTimestamp(timestamp)
                .stream()
                .map(nsiValueConverter::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<NSIValueDto> getByTopicNameAndTimestamp(String topicName, Date timestamp) {
        return nsiValueRepository.findByTopicNameAndTimestamp(topicName, timestamp)
                .stream()
                .map(nsiValueConverter::toDto)
                .collect(Collectors.toSet());
    }
}
