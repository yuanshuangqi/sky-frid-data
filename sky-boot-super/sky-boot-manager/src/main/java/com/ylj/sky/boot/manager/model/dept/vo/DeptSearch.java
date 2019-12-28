package com.ylj.sky.boot.manager.model.dept.vo;

import com.ylj.sky.boot.manager.base.entity.BaseSearch;
import lombok.Data;

@Data
public class DeptSearch extends BaseSearch {
    private String deptName;
    private String deptCode;
}
