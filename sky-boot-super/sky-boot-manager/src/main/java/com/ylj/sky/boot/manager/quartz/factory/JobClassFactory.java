package com.ylj.sky.boot.manager.quartz.factory;

import com.ylj.sky.boot.manager.quartz.job.SayHelloJobLogic;
import org.quartz.Job;

import java.util.HashMap;
import java.util.Map;

public class JobClassFactory {
    private static Map<String,Class<? extends Job>> map  =new HashMap<>();
    static {
        map.put("HelloWorldGroupOne", SayHelloJobLogic.class);
    }
    public static Class<? extends Job> getJobClass(String name){
        return map.get(name);
    }
}
