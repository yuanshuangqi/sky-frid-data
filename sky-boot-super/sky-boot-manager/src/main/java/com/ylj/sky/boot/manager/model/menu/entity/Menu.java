package com.ylj.sky.boot.manager.model.menu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylj.sky.boot.manager.base.entity.BaseEntity;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ApiModel(value = "菜单实体类",description = "用户在系统所能看到的菜单和按钮")
public class Menu extends BaseEntity {
    @ApiModelProperty(value = "菜单code")
    private String menuCode;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "菜单URL")
    private String url;
    private Long parentId = 0L;
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    @ApiModelProperty(value = "是菜单还是按钮 0-菜单;1-按钮")
    @Column(name = "is_menu_or_btn")
    private boolean menuOrBtn; //菜单或者按钮
    @Transient
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"roles"})
    private List<Menu> children;

    public Menu() {
    }

    public Menu(Menu obj) {
        this.menuCode = obj.getMenuCode();
        this.menuName = obj.getMenuName();
        this.parentId = obj.getParentId();
        this.menuOrBtn = obj.isMenuOrBtn();
        this.roles = null;
    }

    @ManyToMany(mappedBy = "menus")
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"menus"})
    private List<Role> roles;
}
