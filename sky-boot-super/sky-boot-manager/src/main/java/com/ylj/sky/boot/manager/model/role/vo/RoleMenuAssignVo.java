package com.ylj.sky.boot.manager.model.role.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "角色-菜单分配实体类",description = "接受角色菜单分配的信息")
public class RoleMenuAssignVo {
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    @ApiModelProperty(value = "角色所分配的菜单ID集合")
    private Long[] menuIds;
}
