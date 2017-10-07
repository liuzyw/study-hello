package com.study.spring.quartz.hello;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created on 2017-10-07
 *
 * @author liuzhaoyuan
 */
public class SimpleTriggerExample {

    public static void main(String[] args) throws Exception {

        JobDetail job = JobBuilder.newJob(HelloJob.class)
            .withIdentity("dummyJobName", "group1").build();

        // 每隔 5s 触发一次

//        Trigger trigger = TriggerBuilder.newTrigger()
//            .withIdentity("dummyTriggerName", "group1")
//            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                    .withIntervalInSeconds(5).repeatForever()).build();

        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("dummyTriggerName", "group1")
            .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
            .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

}
