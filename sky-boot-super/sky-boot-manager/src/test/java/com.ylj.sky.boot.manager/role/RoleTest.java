package com.ylj.sky.boot.manager.role;

import com.ylj.sky.boot.manager.BaseTest;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.model.role.repository.RoleRepository;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

public class RoleTest extends BaseTest {
    @Resource
    private RoleRepository roleRepository;
    @Test
    @Transactional
    @Rollback(value = false)
    public void mergeRoleAndMenu(){
        Role role = roleRepository.findByRoleCodeEquals("ROLE_SUPER_ADMIN");
        Menu menu = new Menu();
        menu.setMenuCode("user_mgr");
        menu.setMenuName("用户管理");
        menu.setParentId(1L);
        role.getMenus().add(menu);
        Menu menu1 = new Menu();
        menu1.setMenuCode("role_mgr");
        menu1.setMenuName("角色管理");
        menu1.setParentId(1L);
        role.getMenus().add(menu1);
        Menu menu2 = new Menu();
        menu2.setMenuCode("menu_mgr");
        menu2.setMenuName("菜单管理");
        menu2.setParentId(1L);
        role.getMenus().add(menu2);
        roleRepository.save(role);
        roleRepository.flush();
    }
}
