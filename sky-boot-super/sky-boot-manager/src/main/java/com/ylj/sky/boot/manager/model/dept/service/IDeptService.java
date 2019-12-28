package com.ylj.sky.boot.manager.model.dept.service;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.dept.entity.Dept;
import com.ylj.sky.boot.manager.model.dept.vo.DeptSearch;

public interface IDeptService {
    R addDept(Dept dept);

    R delDeptById(Long id);

    R editDept(Dept dept);

    R getDeptData(DeptSearch deptSearch);

    R getList();
}
