package com.study.spring.zuul;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import java.io.File;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created on 2018-03-01
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class SpringCloudZuulApplication {

    /**
     * 动态加载
     */
    @PostConstruct
    public void zuulInit() {
        FilterLoader.getInstance().setCompiler(new GroovyCompiler());
        // 读取配置，获取脚本根目录
        String scriptRoot = System.getProperty("zuul.filter.root",
            "/Users/liuzhaoyuan/gitwork/study-hello/spring-cloud-zuul-client/src/main/java//groovy/filter");
        // 获取刷新间隔
        String refreshInterval = System.getProperty("zuul.filter.refreshInterval", "5");
        if (scriptRoot.length() > 0) {
            scriptRoot = scriptRoot + File.separator;
        }
        try {
            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
            FilterFileManager.init(Integer.parseInt(refreshInterval), scriptRoot + "post", scriptRoot + "pre");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudZuulApplication.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--- Hello Spring Cloud SpringCloudZuulApplication ---");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
