package com.liu.drools;

import com.liu.drools.entity.Message;
import org.kie.api.runtime.KieSession;

/**
 * Created on 2019-03-05
 *
 * @author liuzhaoyuan
 */
public class Demo2 {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {
        KieSession kieSession = DroolsUtils.getClient();

        final Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.GOODBYE);

        kieSession.insert(message);
        int count = kieSession.fireAllRules();// 开始执行规则,并获取执行了多少条规则
        kieSession.dispose();// 关闭session


    }


}
