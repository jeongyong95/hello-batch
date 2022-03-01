package io.spring.batch.job.callable;

import io.spring.batch.job.incrementer.TimestampJobParametersIncrementer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.CallableTaskletAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "callableJob")
@Configuration
public class CallableJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TimestampJobParametersIncrementer timestampJobParametersIncrementer;

    private final CallableTaskletAdapter callableTasklet;

    @Bean
    public Job callableJob() {
        return this.jobBuilderFactory.get("callableJob")
            .incrementer(timestampJobParametersIncrementer)
            .start(this.callableStep())
            .build();
    }

    @Bean
    public Step callableStep() {
        return stepBuilderFactory.get("callableStep")
            .tasklet(callableTasklet)
            .build();
    }
}
