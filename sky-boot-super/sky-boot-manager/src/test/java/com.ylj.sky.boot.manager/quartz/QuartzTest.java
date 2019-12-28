package com.ylj.sky.boot.manager.quartz;

import com.ylj.sky.boot.manager.BaseTest;
import com.ylj.sky.boot.manager.quartz.entity.TaskDefine;
import com.ylj.sky.boot.manager.quartz.job.SayHelloJobLogic;
import com.ylj.sky.boot.manager.quartz.service.QuartzJobService;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.JobExecutionContextImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.print.attribute.standard.JobHoldUntil;
import java.util.List;
import java.util.Set;

public class QuartzTest extends BaseTest {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Resource
    private QuartzJobService quartzJobService;


    @Test
    public void test1() throws Exception{
        Set<JobKey> jobKeys = schedulerFactoryBean.getScheduler().getJobKeys(GroupMatcher.anyGroup());
        Set<TriggerKey> triggerKeys = schedulerFactoryBean.getScheduler().getTriggerKeys(GroupMatcher.anyGroup());
        for (TriggerKey triggerKey : triggerKeys) {
            Trigger trigger = schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
            Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(triggerKey);
            System.out.println(trigger);
        }
        for (JobKey jobKey : jobKeys) {
            JobDetail jobDetail = schedulerFactoryBean.getScheduler().getJobDetail(jobKey);
            List<? extends Trigger> triggersOfJob = schedulerFactoryBean.getScheduler().getTriggersOfJob(jobKey);

            System.out.println(jobDetail);
        }
    }
    @Test
    public void test2() throws Exception{
        JobKey jobKey = JobKey.jobKey("HelloWorld", "GroupOne");
        //创建一个定时任务
        TaskDefine task = TaskDefine.builder()
                .jobKey(jobKey)
                .cronExpression("* * 5 * * ? ")   //定时任务 的cron表达式
                .jobClass(SayHelloJobLogic.class)   //定时任务 的具体执行逻辑
                .description("测试任务")    //定时任务 的描述
                .build();
        quartzJobService.scheduleJob(task);
    }
    @Test
    public void test3() throws Exception{
        JobKey jobKey = JobKey.jobKey("HelloWorld", "GroupOne");
        schedulerFactoryBean.getScheduler().triggerJob(jobKey);
    }
    @Test
    public void test4() throws Exception{
        schedulerFactoryBean.getScheduler().triggerJob(JobKey.jobKey("HelloWorld", "GroupOne"));
    }
}
