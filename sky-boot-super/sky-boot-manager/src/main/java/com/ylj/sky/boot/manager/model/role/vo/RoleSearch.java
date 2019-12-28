package com.ylj.sky.boot.manager.model.role.vo;

import com.ylj.sky.boot.manager.base.entity.BaseSearch;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleSearch extends BaseSearch {
    private String roleCode;
    private String roleName;
}
