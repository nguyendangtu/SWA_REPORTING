package miu.edu.swa.pproject.report.controller;

import miu.edu.swa.pproject.report.domain.NSIValue;
import miu.edu.swa.pproject.report.service.KafkaTopicService;
import miu.edu.swa.pproject.report.service.NsiValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final NsiValueService nsiValueService;

    private final KafkaTopicService kafkaTopicService;

    @Autowired
    public ReportController(NsiValueService nsiValueService, KafkaTopicService kafkaTopicService) {
        this.nsiValueService = nsiValueService;
        this.kafkaTopicService = kafkaTopicService;
    }

    @GetMapping("/topics")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> topics() {
        return kafkaTopicService.getAllTopics();
    }

    @GetMapping("/time")
    @ResponseStatus(HttpStatus.OK)
    public Set<NSIValue> getByTime(@RequestParam("from") Long from, @RequestParam("to") Long to) {
        return nsiValueService.getByDuration(from, to);
    }

    @GetMapping("/topic/{topicName}")
    @ResponseStatus(HttpStatus.OK)
    public Set<NSIValue> getByTopicName(@PathVariable("topicName") String topicName) {
        return nsiValueService.getByTopicName(topicName);
    }

    @GetMapping("/topic/{topicName}/time")
    @ResponseStatus(HttpStatus.OK)
    public Set<NSIValue> getByTimeAndTopic(@PathVariable("topicName") String topicName,
                                           @RequestParam("from") Long from, @RequestParam("to") Long to) {
        return nsiValueService.getByTopicNameAndDuration(topicName, from, to);
    }
}
