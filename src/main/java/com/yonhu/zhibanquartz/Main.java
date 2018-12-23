package com.yonhu.zhibanquartz;

import com.yonhu.utils.ConfigUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        JobDetail jobDetail = buildJobDetail();
        Trigger trigger = buildTrigger();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private static JobDetail buildJobDetail() {
        return JobBuilder.newJob(ZhiBanJob.class)
                .withIdentity("myJob", "group1")
                .build();
    }

    private static Trigger buildTrigger() {
        String cron = getCron();
        return TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
    }

    private static String getCron() {
        return ConfigUtil.getConfigValue("cron");
    }
}
