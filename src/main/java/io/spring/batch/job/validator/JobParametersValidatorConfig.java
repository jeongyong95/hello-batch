package io.spring.batch.job.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class JobParametersValidatorConfig {

    private final TimeJobParameterValidator timeJobParameterValidator;

    /*
     * CompositeJobParametersValidator
     * 하나 이상의 JobParametersValidator를 사용하고자 할때 사용한다.
     */
    @Bean
    public CompositeJobParametersValidator compositeJobParametersValidator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        validator.setValidators(List.of(timeJobParameterValidator, this.defaultJobParametersValidator()));
        return validator;
    }

    /*
    * DefaultJobParametersValidator
    * 스프링 배치 프레임워크에서 제공해주는 잡 파라미터 벨리데이터 구현체이다.
     */
    @Bean
    public DefaultJobParametersValidator defaultJobParametersValidator() {
        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator();

        defaultJobParametersValidator.setRequiredKeys(new String[]{"CURRENT_TIMESTAMP"});
        defaultJobParametersValidator.setOptionalKeys(new String[]{"name", "run.id"});

        defaultJobParametersValidator.afterPropertiesSet();
        return defaultJobParametersValidator;
    }
}
