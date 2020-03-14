package com.study.spring.state;

import com.study.spring.state.action.ActionItem;
import com.study.spring.state.action.ActionItemRegistry;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/applicationContext.xml"})
public class StateTest {

    private static final Long serialVersionUID = 1L;


    @Autowired
    private ActionItemRegistry actionItemRegistry;

    @Test
    public void testActionItemRegistry() {
        ActionItem initOrder = actionItemRegistry.getActionItemByCode("initOrder");
        Object o = initOrder.doBiz(null);
        Assert.assertNotNull(o);

    }


}
