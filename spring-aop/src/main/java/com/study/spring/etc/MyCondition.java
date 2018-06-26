package com.study.spring.etc;

import com.study.spring.beans.Hourse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created on 2018-06-25
 *
 * <p>自定义加载类</>
 *
 * @author liuzhaoyuan
 */
public class MyCondition implements Condition {

    /**
     * @param context 判断条件使用的上下文
     * @param metadata 注释信息
     *
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        ClassLoader classLoader = context.getClassLoader();

        Environment environment = context.getEnvironment();

        // bean 定义的注册器
        BeanDefinitionRegistry registry = context.getRegistry();

        ResourceLoader resourceLoader = context.getResourceLoader();

        String property = environment.getProperty("os.name");

        boolean definition = registry.containsBeanDefinition("hourse");

        // mac 操作系统
        if (property.contains("Mac OS X")) {
            Hourse bean = null;
            try {
                bean = beanFactory.getBean(Hourse.class);
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }

        return false;
    }
}
