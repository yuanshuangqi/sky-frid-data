package com.ylj.sky.boot.manager.model.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ApiModel(value = "User对象",description = "用户/员工")
@NamedEntityGraph(name = "userAndRoles",attributeNodes = {@NamedAttributeNode(value = "roles",subgraph = "roles")})
@Table(name = "t_user")
public class User implements Serializable {


    @Column(name = "userid")
    @ApiModelProperty(value = "userid",required = true,example = "ID")
    @Id
    private String id;// ID

    @ApiModelProperty(value = "账号",required = true,example = "admin")
    @Column(name = "username")
    private String account;// 账号

    @ApiModelProperty(value = "密码",notes = "不必填写，系统默认111111",hidden = true)
    private String password;//密码

    @ApiModelProperty(value = "电话号码",required = true,example = "18888888888")
    @Column(name = "tel")
    private String phone;//电话号码

    @ApiModelProperty(value = "性别",example = "1")
    @Column(name = "sex")
    private int sex;//性别

    @ApiModelProperty(value = "ICCard")
    @Column(name = "iccard")
    private  String iCCard;//ICCard

    @ApiModelProperty(value = "是否删除")
    @Column(name = "isdel")
    private  String isDel;//是否删除

    @ApiModelProperty(value = "创建者")
    @Column(name = "creater")
    private  String creater;//创建者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CreatedDate
    @Column(name = "createtime")
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date gmtCreate ;

    @ApiModelProperty(value = "更新者")
    @Column(name = "modifyer")
    private  String modifyer;//更新者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CreatedDate
    @Column(name = "modifytime")
    @ApiModelProperty(value = "更新时间",hidden = true)
    private Date modifytime ;

    @ApiModelProperty(value = "删除者")
    @Column(name = "deleter")
    private  String deleter;//删除者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CreatedDate
    @Column(name = "deletetime")
    @ApiModelProperty(value = "删除时间",hidden = true)
    private Date deleteTime ;

    @ApiModelProperty(value = "用户名")
    @Column(name = "realname")
    private  String realName;//用户名

    @ApiModelProperty(value = "角色Code")
    @Column(name = "fkRolecode")
    private  String roleCode;//角色Code

    @ManyToOne(targetEntity = Dept.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "dept_id",referencedColumnName = "id")
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"users"})
    @ApiModelProperty(value = "部门")
    private Dept dept;
    @ApiModelProperty(value = "一个用户拥有多个角色，多对多")
    @ManyToMany(targetEntity = Role.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "userid")},
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
