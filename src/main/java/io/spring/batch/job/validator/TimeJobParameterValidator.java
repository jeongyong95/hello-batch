package io.spring.batch.job.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
// JobParametersValidator 인터페이스를 구현해서 커스텀 파라미터 벨리데이터를 만들 수 있다.
@Component
public class TimeJobParameterValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        assert parameters != null;
        String timestampParameter = parameters.getString("CURRENT_TIMESTAMP");

        assert timestampParameter != null;
        LocalDateTime validatorTimestamp = LocalDate.of(2022, 3, 12).atStartOfDay();
        if (LocalDateTime.parse(timestampParameter).isBefore(validatorTimestamp)) {
            log.info("내 생일 이전임. CURRENT_TIMESTAMP : {}, ValidatorTimestamp : {}", timestampParameter, validatorTimestamp);
        }
    }
}
