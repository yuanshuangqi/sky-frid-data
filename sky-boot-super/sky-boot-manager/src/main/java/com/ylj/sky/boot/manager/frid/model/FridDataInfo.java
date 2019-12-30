package com.ylj.sky.boot.manager.frid.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FridDataInfo implements Serializable {

    /**过程名称*/
    private String processName;
    /*过程Id*/
    private String processId;
    /*过程总数*/
    private int totalCount;

}
