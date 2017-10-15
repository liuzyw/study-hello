package com.study.spring.quartz.hello;

import com.study.util.date.DateUtils;
import java.util.Date;
import java.util.Map;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * Created on 2017-10-07
 *
 * @author liuzhaoyuan
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(" Hello Quartz --- " + DateUtils.formatDateBySecond(new Date()));

        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println(key.getName());
        System.out.println(key.getGroup());

        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }

        JobDataMap jobDataMap = jobExecutionContext.getTrigger().getJobDataMap();
    }
}
