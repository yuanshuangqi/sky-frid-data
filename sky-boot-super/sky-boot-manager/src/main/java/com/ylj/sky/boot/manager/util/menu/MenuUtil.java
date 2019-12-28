package com.ylj.sky.boot.manager.util.menu;

import cn.hutool.core.collection.CollUtil;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单模块工具类
 */
public class MenuUtil {
    public static List<Menu> getChildrenWithBtn(Menu parentMenu, List<Menu> menus) {
        List<Menu> children = new ArrayList<>();
        for (Menu menu : menus) {
            if(menu.getParentId().equals(parentMenu.getId())){
                children.add(menu);
            }
        }
        if(CollUtil.isNotEmpty(children)){
            for (Menu child : children) {
                child.setChildren(getChildrenWithBtn(child,menus));
            }
        }
        return children;

    }
    public static List<Menu> getChildrenNoBtn(Menu parentMenu, List<Menu> menus) {
        List<Menu> children = new ArrayList<>();
        for (Menu menu : menus) {
            if(menu.getParentId().equals(parentMenu.getId()) && !menu.isMenuOrBtn()){
                children.add(menu);
            }
        }
        if(CollUtil.isNotEmpty(children)){
            for (Menu child : children) {
                child.setChildren(getChildrenNoBtn(child,menus));
            }
        }
        return children;

    }
}
