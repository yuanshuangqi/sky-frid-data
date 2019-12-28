package com.ylj.sky.boot.manager.model.user.query;

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

public class UserDynamicQueryFactory {
    public static BooleanBuilder createDynamicQuery(UserSearch userSearch){
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(StrUtil.isNotBlank(userSearch.getAccount())){
            booleanBuilder.and(qUser.account.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(userSearch.getAccount())));
        }
        if(StrUtil.isNotBlank(userSearch.getCname())){
            booleanBuilder.and(qUser.cname.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(userSearch.getCname())));
        }
        if(StrUtil.isNotBlank(userSearch.getEname())){
            booleanBuilder.and(qUser.ename.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(userSearch.getEname())));
        }
        if(StrUtil.isNotBlank(userSearch.getEmail())){
            booleanBuilder.and(qUser.email.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(userSearch.getEmail())));
        }
        if(StrUtil.isNotBlank(userSearch.getPhone())){
            booleanBuilder.and(qUser.phone.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(userSearch.getPhone())));
        }
        if(StrUtil.isNotBlank(userSearch.getAddress())){
            booleanBuilder.and(qUser.address.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(userSearch.getAddress())));
        }
        if(userSearch.getRoleId() != null){
            booleanBuilder.and(qUser.roles.any().id.eq(userSearch.getRoleId()));
        }
        return booleanBuilder;
    }
}
