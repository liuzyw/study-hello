package special.config;

import feign.Contract;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018-02-28
 *
 * @author liuzhaoyuan
 */
@Configuration
public class FooConfiguration {

    /**
     * 当不希望被全局加载使用时，需要放在外层包下进行隔离
     * @return
     */

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }
}