package miu.edu.swa.pproject.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import miu.edu.swa.pproject.report.dto.NsiReportDto;
import miu.edu.swa.pproject.report.service.KafkaTopicService;
import miu.edu.swa.pproject.report.service.NsiValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CsvReportController {

    private final NsiValueService nsiValueService;

    @Autowired
    public CsvReportController(NsiValueService nsiValueService) {
        this.nsiValueService = nsiValueService;
    }

    @Operation(summary = "Get NSI CSV report by time period")
    @GetMapping("/csv/time")
    @ResponseStatus(HttpStatus.OK)
    public void getByTime(@RequestParam("from") Long from, @RequestParam("to") Long to, HttpServletResponse servletResponse) throws IOException {
//        return nsiValueService.getByDuration(from, to);
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"nsi.csv\"");
        nsiValueService.getByDuration(from, to, servletResponse.getWriter());
    }

    @Operation(summary = "Get NSI csv report by topic name")
    @GetMapping("/csv/topic/{topicName}")
    @ResponseStatus(HttpStatus.OK)
    public void getByTopicName(@PathVariable("topicName") String topicName, HttpServletResponse servletResponse) throws IOException {
//        return nsiValueService.getByTopicName(topicName);
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"nsi.csv\"");
        nsiValueService.getByTopicName(topicName,servletResponse.getWriter());
    }

    @Operation(summary = "Get NSI csv report by topic name and time period")
    @GetMapping("/csv/topic/{topicName}/time")
    @ResponseStatus(HttpStatus.OK)
    public void getByTimeAndTopic(@PathVariable("topicName") String topicName,
                                          @RequestParam("from") Long from, @RequestParam("to") Long to,
                                          HttpServletResponse servletResponse) throws IOException {
//        return nsiValueService.getByTopicNameAndDuration(topicName, from, to);
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"nsi.csv\"");
        nsiValueService.getByTopicNameAndDuration(topicName, from, to,servletResponse.getWriter());
    }
}