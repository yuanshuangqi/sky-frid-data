package com.ylj.sky.boot.manager.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SecurityRole implements GrantedAuthority {
    private Long id;
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private List<Menu> menus = new ArrayList<>();
    public SecurityRole(Menu menu) {
        this.id = menu.getId();
        this.roleCode = menu.getMenuCode();
        this.roleName = menu.getMenuName();
    }

    public SecurityRole(Role role) {
        this.id = role.getId();
        this.roleCode = role.getRoleCode();
        this.roleName = role.getRoleName();
        this.roleDesc = role.getRoleDesc();
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return roleCode;
    }
}
