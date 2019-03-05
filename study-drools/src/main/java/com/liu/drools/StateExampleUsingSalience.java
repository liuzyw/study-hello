package com.liu.drools;

import com.liu.drools.entity.State;
import org.kie.api.runtime.KieSession;

/**
 * Created on 2019-03-05
 *
 * @author liuzhaoyuan
 */
public class StateExampleUsingSalience {

    private static final Long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {
        KieSession kieSession = DroolsUtils.getClient();

        final State a = new State("A");
        final State b = new State("B");
        final State c = new State("C");
        final State d = new State("D");

        a.setState(State.FINISHED);
        b.setState(State.FINISHED);

        kieSession.insert(a);
        kieSession.insert(b);
        kieSession.insert(c);
        kieSession.insert(d);


        int count = kieSession.fireAllRules();// 开始执行规则,并获取执行了多少条规则
        kieSession.dispose();// 关闭session


    }

}
