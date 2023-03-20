package miu.edu.swa.pproject.report.repository;

import miu.edu.swa.pproject.report.domain.NSIValue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface NsiValueRepository extends MongoRepository<NSIValue, Long> {

    Set<NSIValue> findByTopicName(String topicName);
    Set<NSIValue> findByTimestamp(Date timestamp);
    Set<NSIValue> findByTopicNameAndTimestamp(String topicName, Date timestamp);
}
