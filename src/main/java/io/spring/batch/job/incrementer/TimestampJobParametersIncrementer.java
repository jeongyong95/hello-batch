package io.spring.batch.job.incrementer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimestampJobParametersIncrementer implements JobParametersIncrementer {
    private static final String PARAMETER_KEY = "CURRENT_TIMESTAMP";

    public TimestampJobParametersIncrementer() {
    }

    @Override
    public JobParameters getNext(JobParameters parameters) {
        return new JobParametersBuilder(parameters)
            .addString(PARAMETER_KEY, LocalDateTime.now().toString())
            .toJobParameters();
    }
}
