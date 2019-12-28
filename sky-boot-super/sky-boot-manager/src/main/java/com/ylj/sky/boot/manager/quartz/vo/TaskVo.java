package com.ylj.sky.boot.manager.quartz.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class TaskVo implements Serializable {
    private String name;
    private String group;
    private String cron;
}
