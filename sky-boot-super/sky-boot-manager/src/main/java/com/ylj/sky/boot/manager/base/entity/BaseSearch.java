package com.ylj.sky.boot.manager.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class BaseSearch implements Serializable {
    private Long currentPage; //当前页
    private Long pageSize; //当前页面条数
}
