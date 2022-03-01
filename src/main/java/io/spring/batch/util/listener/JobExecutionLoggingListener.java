package io.spring.batch.util.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobExecutionLoggingListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("beforeJob 메서드는 jobExecution 이전에 동작한다.");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob 메서드는 jobExecution 이후에 동작한다.");
    }
}
