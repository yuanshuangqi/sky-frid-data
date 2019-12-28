package com.ylj.sky.boot.manager.util.dept;

import cn.hutool.core.collection.CollUtil;
import com.ylj.sky.boot.manager.model.dept.entity.Dept;

import java.util.ArrayList;
import java.util.List;

public class DeptUtil {
    public static List<Dept> getChildren(Dept parentDept, List<Dept> depts) {
        List<Dept> children = new ArrayList<>();
        for (Dept dept : depts) {
            if(dept.getParentId().equals(parentDept.getId())){
                children.add(dept);
            }
        }
        if(CollUtil.isNotEmpty(children)){
            for (Dept child : children) {
                child.setChildren(getChildren(child,depts));
            }
        }
        return children;

    }
}
