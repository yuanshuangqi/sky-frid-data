package com.ylj.sky.boot.manager.model.user.vo;

import com.ylj.sky.boot.manager.base.entity.BaseSearch;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSearch extends BaseSearch {
    private String account;
    private String cname;
    private String ename;
    private String email;
    private String phone;
    private String address;
    private Long roleId;
}
