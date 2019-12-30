package com.ylj.sky.boot.manager.model.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@ApiModel(value = "用户-角色分配实体类",description = "接受用户分配的角色信息")
@Getter
@Setter
public class UserRoleAssignVo implements Serializable {
    @ApiModelProperty(value = "用户ID")
    private String id;
    @ApiModelProperty(value = "用户所分配的角色ID集合")
    private Long[] roleIds;
}
