package com.ylj.sky.boot.manager.quartz.controller;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.quartz.service.QuartzService;
import com.ylj.sky.boot.manager.quartz.vo.TaskVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/quartz")
public class QuartzController {
    @Resource
    private QuartzService quartzService;
    /**
     * 修改定时任务cron表达式
     */
    @PutMapping(value = "/modifyCron")
    public R modifyCron(@RequestBody TaskVo taskVo){
        return quartzService.modifyCron(taskVo);
    }
    /**
     * 立即执行
     */
    @PutMapping(value = "/executeImmediately/{name}/{group}")
    public R executeImmediately(@PathVariable(value = "name")String name,@PathVariable(value = "group")String group){
       return quartzService.executeImmediately(name,group);
    }
    /**
     * 恢复全部任务
     * @return R
     */
    @PutMapping(value = "/resumeAll")
    public R resumeAllTask(){
        return quartzService.resumeAll();
    }
    /**
     * 暂停全部任务
     * @return R
     */
    @PutMapping(value = "/pauseAll")
    public R pauseAllTask(){
        return quartzService.pauseAll();
    }
    /**
     * 删除任务
     * @param name 任务名称
     * @param group 任务分组
     * @return R
     */
    @PutMapping(value = "/del/{name}/{group}")
    public R del(@PathVariable(value = "name")String name,@PathVariable(value = "group")String group){
        return quartzService.del(name,group);
    }
    /**
     * 暂停任务
     * @param name 任务名称
     * @param group 任务分组
     * @return R
     */
    @PutMapping(value = "/pause/{name}/{group}")
    public R pause(@PathVariable(value = "name")String name,@PathVariable(value = "group")String group){
        return quartzService.pause(name,group);
    }
    /**
     * 开始/恢复任务
     * @param name 任务名称
     * @param group 任务分组
     * @return R
     */
    @PutMapping(value = "/start/{name}/{group}")
    public R start(@PathVariable(value = "name")String name,@PathVariable(value = "group")String group){
        return quartzService.start(name,group);
    }
    @GetMapping(value = "/list")
    public R list(){
        return quartzService.list();
    }
}
