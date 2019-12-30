package com.ylj.sky.boot.manager.security.factory;

import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.model.user.entity.User;
import com.ylj.sky.boot.manager.security.entity.SecurityRole;
import com.ylj.sky.boot.manager.security.entity.SecurityUser;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

public class SecurityFactory {
    //私有化构造器
    private SecurityFactory() {
    }
    public static SecurityUser login(User user){
        SecurityUser securityUser =new SecurityUser();
        securityUser.setUsername(user.getAccount());
        securityUser.setPassword(user.getPassword());
        return securityUser;
    }
    public static SecurityUser login(SecurityUser securityUser){
        SecurityUser user =new SecurityUser();
        user.setUsername(securityUser.getUsername());
        return user;
    }
    public static SecurityUser create(User user){
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(user.getAccount());
        securityUser.setPassword(user.getPassword());
//        securityUser.setEmail(user.getEmail());
        securityUser.setPhone(user.getPhone());
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            SecurityRole securityRole = new SecurityRole(role);
            securityUser.getSecurityRoles().add(securityRole);
        }
        List<Menu> menus = roles.stream().map(Role::getMenus).flatMap(Collection::stream).collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Menu::getId))), ArrayList::new));
        for (Menu menu : menus) {
            SecurityRole securityRole = new SecurityRole(menu);
            securityUser.getSecurityRoles().add(securityRole);
        }
        return securityUser;
    }
    public static SecurityUser create(SecurityUser user){
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(user.getUsername());
        securityUser.setPassword(user.getPassword());
        securityUser.setEmail(user.getEmail());
        securityUser.setPhone(user.getPhone());
       securityUser.setSecurityRoles(user.getSecurityRoles());
        return securityUser;
    }
}
