package com.liu.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created on 2019-03-05
 *
 * @author liuzhaoyuan
 */
public class DroolsUtils {

    private static final Long serialVersionUID = 1L;


    public static KieSession getClient() {
        KieServices kieServices = KieServices.Factory.get(); // 通过这个静态方法去获取一个实例
        KieContainer kieContainer = kieServices.getKieClasspathContainer();// 默认去读取配置文件
        KieSession kieSession = kieContainer.newKieSession("all-rules");// 根据这个名词去获取kieSession

        kieSession.addEventListener(new org.kie.api.event.rule.DebugAgendaEventListener());
        kieSession.addEventListener(new org.kie.api.event.rule.DebugRuleRuntimeEventListener());

        return kieSession;
    }

}
