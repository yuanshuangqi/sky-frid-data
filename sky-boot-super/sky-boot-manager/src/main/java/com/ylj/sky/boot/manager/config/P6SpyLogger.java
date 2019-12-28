package com.ylj.sky.boot.manager.config;

import cn.hutool.core.util.StrUtil;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.ylj.sky.boot.common.util.SQLFormatterUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * p6spy打印日志输出格式修改
 * 1.只打印最终执行的sql.
 * 2.sql换到下一行
 * 3.结尾处增加分号,以标示sql结尾
 * Created by odelia on 2016/1/4.
 */
public class P6SpyLogger implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connection, String now, long executionTime, String s1, String sqlNoParam, String sqlParam, String s4) {
        // 在服务器启动以后，每隔几秒至几十秒就会轮询StdJDBCDelegate类中selectTriggerToAcquire方法，
        // 执行sql查询qrtz_triggers表中NEXT_FIRE_TIME（下次触发时间）字段符合当前时间段并且TRIGGER_STATE状态为WAITING（等待中），
        // 符合此条件的Trigger查询出来，关联jobDetail来得到配置参数，调用配置好的业务方法。
        // 控制台不打印相关sql
        if(!sqlParam.contains("TRIGGER_NAME") && !sqlParam.contains("SELECT 1") && StrUtil.isNotBlank(sqlNoParam)){
            return "\n"+"连接数："+connection+" 耗时:"+executionTime+"\n"+ SQLFormatterUtil.format(sqlParam);
        }else {
            return "";
        }

    }
}