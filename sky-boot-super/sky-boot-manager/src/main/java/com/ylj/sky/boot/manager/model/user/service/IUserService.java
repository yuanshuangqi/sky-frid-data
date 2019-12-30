package com.ylj.sky.boot.manager.model.user.service;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.user.entity.User;
import com.ylj.sky.boot.manager.model.user.vo.UserRoleAssignVo;
import com.ylj.sky.boot.manager.model.user.vo.UserSearch;
import com.ylj.sky.boot.manager.security.entity.SecurityUser;

public interface IUserService {
    /**
     * 新增人员
     */
    R addUser(User user);
    /**
     * 修改人员
     */
    R editUser(User user);
    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return R
     */
    R getUserInfoByUsername(String username);

    R getUserData(UserSearch userSearch);

    R delUserByUserId(String id);

    R userRoleAssign(UserRoleAssignVo roleAssignVo);

    SecurityUser getUserRolesByUsername(String username);
}
