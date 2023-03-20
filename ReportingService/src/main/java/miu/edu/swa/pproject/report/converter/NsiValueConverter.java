package miu.edu.swa.pproject.report.converter;

import miu.edu.swa.pproject.report.domain.NSIValue;
import miu.edu.swa.pproject.report.dto.NSIValueDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface NsiValueConverter extends Converter<NSIValue, NSIValueDto> {
}
