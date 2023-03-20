package miu.edu.swa.pproject.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class NSIValueDto {
    private Long id;
    private Date timestamp;

    private Double value;

    private String topicName;
}
