package io.spring.batch.job.callable.step.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.step.tasklet.CallableTaskletAdapter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Callable;

@Slf4j
@Configuration
public class CallableTaskletConfig {

    // CallableTasklet에서 수행하고자 하는 로직이다.
    @Bean
    public Callable<RepeatStatus> callableObject() {
        return () -> {
            log.info("새 스레드에서 실행되는 callable Tasklet의 작업");
            return RepeatStatus.FINISHED;
        };
    }

    // CallableTaskletAdapter는 Tasklet과 InitializingBean을 구현한다.
    @Bean
    public CallableTaskletAdapter callableTasklet() {
        CallableTaskletAdapter callableTaskletAdapter = new CallableTaskletAdapter();
        callableTaskletAdapter.setCallable(this.callableObject());

        return callableTaskletAdapter;
    }
}
