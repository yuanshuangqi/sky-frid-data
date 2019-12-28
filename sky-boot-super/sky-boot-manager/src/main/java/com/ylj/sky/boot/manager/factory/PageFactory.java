package com.ylj.sky.boot.manager.factory;


import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageFactory {
    public static Map<String,Object> createPageMap(Page page){
        Map<String,Object> map = new HashMap<>();
        map.put("total",page.getTotalElements());
        map.put("rows",page.getContent());
        return map;
    }
    public static Map<String,Object> createPageMap(QueryResults results){
        Map<String,Object> map = new HashMap<>();
        map.put("total",results.getTotal());
        map.put("rows",results.getResults());
        return map;
    }
    public static Map<String,Object> createPageMap(List list){
        Map<String,Object> map = new HashMap<>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }
}
