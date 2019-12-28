package com.ylj.sky.boot.manager.model.dept.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylj.sky.boot.manager.base.entity.BaseEntity;
import com.ylj.sky.boot.manager.model.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ApiModel(value = "部门实体类")
public class Dept extends BaseEntity {
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    @ApiModelProperty(value = "部门code")
    private String deptCode;
    @ApiModelProperty(value = "上级部门ID")
    private Long parentId = 0L;
    @OneToMany(mappedBy = "dept")
    @JsonIgnoreProperties(ignoreUnknown = true,value = {"dept"})
    @ApiModelProperty(value = "部门人员")
    private Set<User> users = new HashSet<>();
    @Transient
    private List<Dept> children;
}
