package miu.edu.swa.pproject.report.controller;

import miu.edu.swa.pproject.report.domain.NSIValue;
import miu.edu.swa.pproject.report.service.NsiValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/report")
public class ReportController {

    private NsiValueService nsiValueService;

    @Autowired
    public ReportController(NsiValueService nsiValueService) {
        this.nsiValueService = nsiValueService;
    }

    @GetMapping("/timestamp/{timestamp}")
    @ResponseStatus(HttpStatus.OK)
    public Set<NSIValue> getByTimestamp(@PathVariable("timestamp") Date timestamp) {
        return nsiValueService.getByTimestamp(timestamp);
    }

    @GetMapping("/topic/{topicName}")
    @ResponseStatus(HttpStatus.OK)
    public Set<NSIValue> getByTopicName(@PathVariable("topicName") String topicName) {
        return nsiValueService.getByTopicName(topicName);
    }

    @GetMapping("/topic/{topicName}/timestamp/{timestamp}")
    @ResponseStatus(HttpStatus.OK)
    public Set<NSIValue> getByTimestampAndTopic(@PathVariable("topicName") String topicName,
                                                   @PathVariable("timestamp") Date timestamp) {
        return nsiValueService.getByTopicNameAndTimestamp(topicName, timestamp);
    }
}
