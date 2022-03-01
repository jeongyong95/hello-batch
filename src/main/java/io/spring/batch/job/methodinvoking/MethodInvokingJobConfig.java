package io.spring.batch.job.methodinvoking;

import io.spring.batch.job.incrementer.TimestampJobParametersIncrementer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "methodInvoking")
@Configuration
public class MethodInvokingJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TimestampJobParametersIncrementer timestampJobParametersIncrementer;

    private final MethodInvokingTaskletAdapter methodInvokingTasklet;

    @Bean
    public Job methodInvokingJob() {
        return jobBuilderFactory.get("methodInvoking")
            .start(this.methodInvokingStep())
            .incrementer(timestampJobParametersIncrementer)
            .build();
    }

    @Bean
    public Step methodInvokingStep() {
        return stepBuilderFactory.get("methodInvokingStep")
            .tasklet(methodInvokingTasklet)
            .build();
    }
}
