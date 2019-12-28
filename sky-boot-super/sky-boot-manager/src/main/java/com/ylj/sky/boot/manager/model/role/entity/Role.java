package com.ylj.sky.boot.manager.model.role.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylj.sky.boot.manager.base.entity.BaseEntity;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ApiModel(value = "角色对象",description = "用户在系统中的角色")
public class Role  extends BaseEntity {
    @ApiModelProperty(value = "角色Code",required = true)
    private String roleCode;
    @ApiModelProperty(value = "角色名称",required = true)
    private String roleName;
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;
    @ApiModelProperty(value = "父级角色ID")
    private Long parentId = 0L;

    @ManyToMany(mappedBy = "roles")
    @ApiModelProperty(value = "一个角色被多个用户拥有，多对多")
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"roles","treeMenus","allMenus"})
    private List<User> users = new ArrayList<>();

    @ManyToMany(targetEntity = Menu.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @ApiModelProperty(value = "一个角色用户多个菜单或者按钮权限,多对多")
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"roles"})
    @JoinTable(name = "role_menu",
    joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")})
    private List<Menu> menus = new ArrayList<>();

    @Transient
    private List<Role> children;


}
