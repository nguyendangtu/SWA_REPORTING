package miu.edu.swa.pproject.report.service.impl;

import miu.edu.swa.pproject.report.domain.NSIValue;
import miu.edu.swa.pproject.report.repository.NsiValueRepository;
import miu.edu.swa.pproject.report.service.NsiValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class NsiValueServiceImpl implements NsiValueService {

    private NsiValueRepository nsiValueRepository;
    @Autowired
    public NsiValueServiceImpl(NsiValueRepository nsiValueRepository) {
        this.nsiValueRepository = nsiValueRepository;
    }

    @Override
    public Set<NSIValue> getByTopicName(String topicName) {
        return nsiValueRepository.findByTopicName(topicName);
    }

    @Override
    public Set<NSIValue> getByTimestamp(Date timestamp) {
        return nsiValueRepository.findByTimestamp(timestamp);
    }

    @Override
    public Set<NSIValue> getByTopicNameAndTimestamp(String topicName, Date timestamp) {
        return nsiValueRepository.findByTopicNameAndTimestamp(topicName, timestamp);
    }

    @Override
    public NSIValue saveRecord(Double value, Long timestamp, String topicName) {
        NSIValue nsiValue = new NSIValue();
        nsiValue.setValue(value);
        nsiValue.setTimestamp(timestamp);
        nsiValue.setTopicName(topicName);
        return nsiValueRepository.save(nsiValue);
    }
}
