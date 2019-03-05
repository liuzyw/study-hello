package com.liu.drools;

import com.liu.drools.entity.Car;
import com.liu.drools.entity.Person;
import org.kie.api.runtime.KieSession;

/**
 * Created on 2019-03-05
 *
 * @author liuzhaoyuan
 */
public class Demo1 {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {
        KieSession kieSession = DroolsUtils.getClient();

        Person p1 = new Person();
        p1.setAge(30);
        Car c1 = new Car();
        c1.setPerson(p1);

        Person p2 = new Person();
        p1.setAge(70);
        Car c2 = new Car();
        c2.setPerson(p2);

        kieSession.insert(c1); // 将c1实例放入到session中,
        kieSession.insert(c2); //

        int count = kieSession.fireAllRules();// 开始执行规则,并获取执行了多少条规则
        kieSession.dispose();// 关闭session
        System.out.println("Fire " + count + " rule(s)!");
        System.out.println("The discount of c1 is " + c1.getDiscount() + "%");
        System.out.println("The discount of c2 is " + c2.getDiscount() + "%");


    }


}
