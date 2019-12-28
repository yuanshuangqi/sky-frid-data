package com.ylj.sky.boot.manager.model.menu.service;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.menu.vo.MenuSearch;

public interface IMenuService {
    R addMenu(Menu menu);

    R delMenuById(Long id);

    R editMenu(Menu menu);

    R getMenuData(MenuSearch menuSearch);

    R getList();


}
