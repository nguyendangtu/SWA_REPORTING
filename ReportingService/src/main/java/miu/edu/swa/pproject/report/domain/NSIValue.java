package miu.edu.swa.pproject.report.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NSIValue {
    @Id
    private Long id;
    private Double value;
    private Long timestamp;
    private String topicName;
}
