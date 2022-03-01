package io.spring.batch.job.hello;

import io.spring.batch.job.incrementer.TimestampJobParametersIncrementer;
import io.spring.batch.job.listener.JobExecutionLoggingListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
// 환경변수에 해당 name의 value값이 존재할 때, 이 configuration class을 import한다.
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "helloJob")
@RequiredArgsConstructor
@Configuration
public class HelloJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final TimestampJobParametersIncrementer timestampJobParametersIncrementer;
    private final CompositeJobParametersValidator compositeJobParametersValidator;

    private final JobExecutionLoggingListener jobExecutionLoggingListener;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("basicJob")
            .start(this.step())
            // JobParametersIncrementer는 JobParameter에 매번 변하는 파라미터를 추가해서 jobInstance가 새로 생성되게 만든다.
            .incrementer(timestampJobParametersIncrementer)
            .listener(jobExecutionLoggingListener)
            .validator(compositeJobParametersValidator)
            .build();
    }

    @Bean
    public Step step() {
        return this.stepBuilderFactory.get("step1")
            .tasklet(((contribution, chunkContext) -> {
                log.info("Hello, batch!");
                return RepeatStatus.FINISHED;
            }))
            .build();
    }
}
