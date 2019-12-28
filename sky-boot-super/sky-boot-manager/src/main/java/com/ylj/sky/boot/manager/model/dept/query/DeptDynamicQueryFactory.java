package com.ylj.sky.boot.manager.model.dept.query;

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

public class DeptDynamicQueryFactory {
    public static BooleanBuilder createDynamicQuery(DeptSearch deptSearch){
        QDept qDept = QDept.dept;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(deptSearch != null){
            if(StrUtil.isNotBlank(deptSearch.getDeptName())){
                booleanBuilder.and(qDept.deptName.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(deptSearch.getDeptName())));
            }
            if(StrUtil.isNotBlank(deptSearch.getDeptCode())){
                booleanBuilder.and(qDept.deptCode.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(deptSearch.getDeptCode())));
            }
        }
        return booleanBuilder;
    }
}
