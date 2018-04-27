package special.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created on 2018-04-22
 *
 * @author liuzhaoyuan
 */
@Configuration
public class FooConfiguration2 {

    /**
     * 禁用 hystrix
     * @return
     */
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }


}
