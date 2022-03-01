package io.spring.batch.job.methodinvoking.step.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingService {

    public void getComponentName() {
        log.info("MethodInvokingTasklet 실행. 대상 컴포넌트 : {}", this.getClass().getName());
    }
}
