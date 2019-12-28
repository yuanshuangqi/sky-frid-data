package com.ylj.sky.boot.manager.model.role.query;

import cn.hutool.core.util.StrUtil;
import com.querydsl.core.BooleanBuilder;
import com.ylj.sky.boot.common.util.SkyBootUtil;
import com.ylj.sky.boot.manager.model.dept.entity.QDept;
import com.ylj.sky.boot.manager.model.dept.vo.DeptSearch;
import com.ylj.sky.boot.manager.model.menu.entity.QMenu;
import com.ylj.sky.boot.manager.model.menu.vo.MenuSearch;
import com.ylj.sky.boot.manager.model.role.entity.QRole;
import com.ylj.sky.boot.manager.model.role.vo.RoleSearch;
import com.ylj.sky.boot.manager.model.user.entity.QUser;
import com.ylj.sky.boot.manager.model.user.vo.UserSearch;

public class RoleDynamicQueryFactory {
    public static BooleanBuilder createDynamicQuery(RoleSearch roleSearch){
        QRole qRole = QRole.role;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(roleSearch != null){
            if(StrUtil.isNotBlank(roleSearch.getRoleCode())){
                booleanBuilder.and(qRole.roleCode.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(roleSearch.getRoleCode())));
            }
            if(StrUtil.isNotBlank(roleSearch.getRoleName())){
                booleanBuilder.and(qRole.roleName.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(roleSearch.getRoleName())));
            }
        }
        return booleanBuilder;
    }
}
