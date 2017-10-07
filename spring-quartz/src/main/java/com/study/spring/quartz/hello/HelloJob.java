package com.study.spring.quartz.hello;

import com.study.util.date.DateUtils;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created on 2017-10-07
 *
 * @author liuzhaoyuan
 */
public class HelloJob implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(" Hello Quartz --- " + DateUtils.formatDateBySecond(new Date()));
    }
}
