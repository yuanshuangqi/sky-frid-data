package com.ylj.sky.boot.manager.model.menu.query;

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

public class MenuDynamicQueryFactory {
    public static BooleanBuilder createDynamicQuery(MenuSearch menuSearch){
        QMenu qMenu = QMenu.menu;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(menuSearch != null){
            if(StrUtil.isNotBlank(menuSearch.getMenuName())){
                booleanBuilder.and(qMenu.menuName.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(menuSearch.getMenuName())));
            }
            if(StrUtil.isNotBlank(menuSearch.getMenuCode())){
                booleanBuilder.and(qMenu.menuCode.likeIgnoreCase(SkyBootUtil.getFuzzyQuery(menuSearch.getMenuCode())));
            }
        }
        return booleanBuilder;
    }
}
