package com.ylj.sky.boot.manager.model.role.service;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.model.role.vo.RoleMenuAssignVo;
import com.ylj.sky.boot.manager.model.role.vo.RoleSearch;

public interface IRoleService {
    R addRole(Role role);

    R editRole(Role role);

    R getRoleData(RoleSearch roleSearch);

    R delRoleById(Long id);

    R getList();

    R menuAssign(RoleMenuAssignVo roleMenuAssignVo);
}
