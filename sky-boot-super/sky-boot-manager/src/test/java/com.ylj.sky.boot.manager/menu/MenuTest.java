package com.ylj.sky.boot.manager.menu;

import com.ylj.sky.boot.manager.BaseTest;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.menu.repository.MenuRepository;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;

public class MenuTest extends BaseTest {
    @Resource
    private MenuRepository menuRepository;
    @Test
    @Transactional
    @Rollback(value = false)
    public void addMenu(){
        Menu menu =new Menu();
        menu.setMenuCode("role_mgr");
        menu.setMenuName("角色管理");
        menuRepository.saveAndFlush(menu);
    }
}
