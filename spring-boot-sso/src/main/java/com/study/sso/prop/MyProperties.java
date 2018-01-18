package com.study.sso.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 2018-01-18
 *
 * <p>自定义属性配置</p>
 *
 * @author liuzhaoyuan
 */
@Component
@ConfigurationProperties(prefix = "my")
@Data
public class MyProperties {

    private String value;

    private String book;

}
