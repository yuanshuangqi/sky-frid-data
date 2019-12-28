package com.ylj.sky.boot.manager.model.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.ylj.sky.boot.manager.base.entity.BaseEntity;
import com.ylj.sky.boot.manager.model.dept.entity.Dept;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ApiModel(value = "User对象",description = "用户/员工")
@NamedEntityGraph(name = "userAndRoles",attributeNodes = {@NamedAttributeNode(value = "roles",subgraph = "roles")})
public class User extends BaseEntity {
    @ApiModelProperty(value = "账号",required = true,example = "admin")
    private String account;// 账号

    @ApiModelProperty(value = "密码",notes = "不必填写，系统默认111111",hidden = true)
    private String password;//密码

    @ApiModelProperty(value = "中文名",required = true,example = "金木")
    private String cname; //中文名

    @ApiModelProperty(value = "英文名")
    private String ename; //英文名

    @ApiModelProperty(value = "邮箱",required = true,example = "793455561@qq.com")
    private String email;// 邮箱

    @ApiModelProperty(value = "电话号码",required = true,example = "18888888888")
    private String phone;//电话号码

    @ApiModelProperty(value = "住址",notes = "非必填项",example = "火星")
    private String address; //住址
    @ManyToOne(targetEntity = Dept.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "dept_id",referencedColumnName = "id")
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"users"})
    @ApiModelProperty(value = "部门")
    private Dept dept;
    @ApiModelProperty(value = "一个用户拥有多个角色，多对多")
    @ManyToMany(targetEntity = Role.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"users","menus"})
    private List<Role> roles = new ArrayList<>();

    @Transient
    @ApiModelProperty(value = "用户菜单和按钮权限的树形结构",hidden = true)
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"roles"})
    private List<Menu> treeMenus;

    @Transient
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"roles"})
    private List<Menu> allMenus;


}
