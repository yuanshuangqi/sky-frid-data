package com.ylj.sky.boot.manager.util.role;

import cn.hutool.core.collection.CollUtil;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.model.role.repository.RoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Component
public class RoleUtil {
    /**
     * 递归封装子角色
     * @param parentRole
     * @param roles
     * @return
     */
    public static List<Role> getChildren(Role parentRole, List<Role> roles) {
        List<Role> children = new ArrayList<>();
        for (Role role : roles) {
            if(role.getParentId().equals(parentRole.getId())){
                children.add(role);
            }
        }
        if(CollUtil.isNotEmpty(children)){
            for (Role child : children) {
                child.setChildren(getChildren(child,roles));
            }
        }
        return children;

    }
}
