package com.ylj.sky.boot.manager.quartz.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
public class TaskDefine implements Serializable {
    /**
     * 定时任务 的名字和分组名 JobKey,{@link org.quartz.JobKey}
     */
    private JobKey jobKey;
    /**
     * 定时任务trigger
     */
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"scheduleBuilder","triggerBuilder"})
    private Trigger trigger;
    /**
     * 定时任务状态
     */
    private Trigger.TriggerState triggerState;
    /**
     * 定时任务 的描述(可以定时任务本身的描述,也可以是触发器的)
     * {@link org.quartz.JobDetail} description ; {@link org.quartz.Trigger} description
     */
    private String description;

    /**
     * 定时任务 的执行cron (Trigger的CronScheduleBuilder 的cronExpression)
     * {@link org.quartz.Trigger} CronScheduleBuilder {@link org.quartz.CronScheduleBuilder}
     */
    private String cronExpression;

    /**
     * 定时任务 的元数据
     * {@link org.quartz.JobDataMap}
     */
    private Map<?, ?> jobDataMap;

    /**
     * 定时任务 的 具体执行逻辑类
     * {@link org.quartz.Job}
     */
    private Class<? extends Job> jobClass;
}
