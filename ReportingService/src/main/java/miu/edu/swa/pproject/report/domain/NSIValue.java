package miu.edu.swa.pproject.report.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NSIValue {
    @Id
    private Long id;
    private Date timestamp;

    private Double value;

    private String topicName;
}
