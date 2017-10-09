package com.study.spring.quartz.pojob;

import com.study.util.date.DateUtils;
import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created on 2017-10-09
 *
 * @author liuzhaoyuan
 */
public class QuartzJobBeanTest extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(" Hello Quartz QuartzJobBean Job--- " + DateUtils.formatDateBySecond(new Date()));

    }

    public void testMet(){
        System.out.println(" Hello Quartz QuartzJobBean Job--- " + DateUtils.formatDateBySecond(new Date()));
    }
}
