package com.ylj.sky.boot.manager.quartz.service;

import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.quartz.entity.TaskDefine;
import com.ylj.sky.boot.manager.quartz.factory.JobClassFactory;
import com.ylj.sky.boot.manager.quartz.vo.TaskVo;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class QuartzService {
    //Quartz定时任务核心的功能实现类
    private Scheduler scheduler;
    @Resource
    private QuartzJobService quartzJobService;

    public QuartzService(@Autowired SchedulerFactoryBean schedulerFactoryBean) {
        scheduler = schedulerFactoryBean.getScheduler();
    }


    public R list() {
        List<TaskDefine> taskDefineList = new ArrayList<>();
        try {
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyGroup());
            for (TriggerKey triggerKey : triggerKeys) {
                if (!triggerKey.getGroup().equalsIgnoreCase("DEFAULT")) {
                    Trigger trigger = scheduler.getTrigger(triggerKey);
                    JobDetail jobDetail = scheduler.getJobDetail(trigger.getJobKey());
                    taskDefineList.add(TaskDefine.builder().trigger(scheduler.getTrigger(triggerKey)).triggerState(scheduler.getTriggerState(triggerKey)).description(jobDetail.getDescription()).build());
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return R.ok().data(taskDefineList);
    }

    /**
     * 开始某个任务
     *
     * @param name  任务名称
     * @param group 任务分组
     * @return R
     */
    public R start(String name, String group) {
        try {
            quartzJobService.resumeJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return R.setResult(ResultCodeEnum.START_SUCCESS);
    }

    /**
     * 暂停任务
     *
     * @param name  任务名称
     * @param group 任务分组
     * @return R
     */
    public R pause(String name, String group) {
        try {
            quartzJobService.pauseJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return R.setResult(ResultCodeEnum.PAUSED_SUCCESS);
    }

    /**
     * 删除任务
     *
     * @param name  任务名称
     * @param group 任务分组
     * @return R
     */
    public R del(String name, String group) {
        try {
            quartzJobService.deleteJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return R.setResult(ResultCodeEnum.DEL_SUCCESS);
    }

    /**
     * 暂停全部任务
     *
     * @return R
     */
    public R pauseAll() {
        try {
            quartzJobService.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return R.setResult(ResultCodeEnum.PAUSED_SUCCESS);
    }

    /**
     * 恢复全部任务
     *
     * @return R
     */
    public R resumeAll() {
        try {
            quartzJobService.resumeAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return R.setResult(ResultCodeEnum.START_SUCCESS);
    }

    /**
     * 立即执行
     *
     * @param name  任务名称
     * @param group 任务分组
     * @return R
     */
    public R executeImmediately(String name, String group) {
        try {
            quartzJobService.executeImmediately(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return R.setResult(ResultCodeEnum.START_SUCCESS);
    }

    public R modifyCron(TaskVo taskVo) {
        //这是即将要修改cron的定时任务
        TaskDefine modifyCronTask = TaskDefine.builder()
                .jobKey(JobKey.jobKey(taskVo.getName(),taskVo.getGroup()))
                .cronExpression(taskVo.getCron())   //定时任务 的cron表达式
                .jobClass(JobClassFactory.getJobClass(taskVo.getName()+taskVo.getGroup()))   //定时任务 的具体执行逻辑
                .description("这是一个测试的 任务")    //定时任务 的描述
                .build();
       return quartzJobService.modifyJobCron(modifyCronTask)?R.setResult(ResultCodeEnum.EDIT_SUCCESS):R.setResult(ResultCodeEnum.EDIT_FAIL);

    }
}
