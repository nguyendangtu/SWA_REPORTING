package miu.edu.swa.pproject.report.kafka;


import miu.edu.swa.pproject.report.service.NsiValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class NsiReceiver {
    NsiValueService nsiValueService;

    @Autowired
    public NsiReceiver(NsiValueService nsiValueService) {
        this.nsiValueService = nsiValueService;
    }

    @KafkaListener(topics = {"${app.topic.nsi-topic-name}"})
    public void receive(@Payload Double value,
                        @Headers MessageHeaders headers,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        nsiValueService.saveRecord(value, headers.getTimestamp(), topicName);
    }

}