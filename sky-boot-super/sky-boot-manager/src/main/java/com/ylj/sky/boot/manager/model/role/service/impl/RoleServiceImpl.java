package com.ylj.sky.boot.manager.model.role.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.factory.PageFactory;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.menu.repository.MenuRepository;
import com.ylj.sky.boot.manager.model.role.entity.QRole;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.model.role.query.RoleDynamicQueryFactory;
import com.ylj.sky.boot.manager.model.role.repository.RoleRepository;
import com.ylj.sky.boot.manager.model.role.service.IRoleService;
import com.ylj.sky.boot.manager.model.role.vo.RoleMenuAssignVo;
import com.ylj.sky.boot.manager.model.role.vo.RoleSearch;
import com.ylj.sky.boot.manager.util.role.RoleUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private JPAQueryFactory queryFactory;

    @Override
    @Transactional
    public R addRole(Role role) {
        roleRepository.save(role);
        return R.setResult(ResultCodeEnum.ADD_SUCCESS);
    }
    @Override
    @Transactional
    public R delRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if(roleOptional.isPresent()){
            List<Menu> menus = roleOptional.get().getMenus();
            if(CollUtil.isNotEmpty(menus)){
                menus.forEach(menu -> menu.getRoles().remove(roleOptional.get()));
            }
            List<Role> children = roleRepository.findByParentIdEquals(id);
            if(CollUtil.isNotEmpty(children)){
                for (Role child : children) {
                    List<Menu> childMenus = child.getMenus();
                    if(CollUtil.isNotEmpty(childMenus)){
                        childMenus.forEach(menu -> menu.getRoles().remove(roleOptional.get()));
                    }
                }
                roleRepository.deleteByParentIdEquals(roleOptional.get().getId());
            }
        }
        roleRepository.deleteById(id);
        return R.setResult(ResultCodeEnum.DEL_SUCCESS);
    }
    @Override
    @Transactional
    public R editRole(Role role) {
        //父级角色不能是自身
        if(role.getId().equals(role.getParentId())){
            return R.setResult(ResultCodeEnum.ROLE_NO_SELF);
        }
        List<Role> children = roleRepository.findByParentIdEquals(role.getId());
        if(CollUtil.isNotEmpty(children)){
            //  父角色不是子角色
            if(children.stream().anyMatch(e->e.getId().equals(role.getParentId()))){
                return R.setResult(ResultCodeEnum.ROLE_NO_CHILDREN);
            }
        }
        Optional<Role> roleOptional = roleRepository.findById(role.getId());
        if(roleOptional.isPresent()){
            role.setMenus(roleOptional.get().getMenus());
            role.setGmtCreate(roleOptional.get().getGmtCreate());
        }
        roleRepository.save(role);
        return R.setResult(ResultCodeEnum.EDIT_SUCCESS);
    }

    @Override
    @Transactional
    public R menuAssign(RoleMenuAssignVo roleMenuAssignVo) {
        Long roleId = roleMenuAssignVo.getRoleId();
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if(roleOptional.isPresent()){
            Role role = roleOptional.get();
            Long[] menuIds = roleMenuAssignVo.getMenuIds();
            // 删除用户的所有角色信息
            role.getMenus().clear();
            if(menuIds != null && menuIds.length > 0){
                List<Menu> menus = menuRepository.findAllByIdIn(menuIds);
                role.setMenus(menus);
            }
            roleRepository.save(role);
            return R.setResult(ResultCodeEnum.MENU_ASSIGN_SUCCESS);
        }
        return R.error();
    }

    @Override
    public R getRoleData(RoleSearch roleSearch) {
        QRole qRole = QRole.role;
        QueryResults<Role> roleQueryResults = queryFactory.selectFrom(qRole).where(RoleDynamicQueryFactory.createDynamicQuery(roleSearch)).fetchResults();
        // 获取所有角色
        List<Role> allRole = roleQueryResults.getResults();
        // 获取所有父级角色
        List<Role> parentRoles = allRole.stream().filter(e -> e.getParentId() == 0L).collect(Collectors.toList());
        //递归封装子角色
        for (Role role : parentRoles) {
            role.setChildren(RoleUtil.getChildren(role,allRole));
        }
        return R.ok().data(PageFactory.createPageMap(parentRoles));
    }
    @Override
    public R getList() {
        return R.ok().data(roleRepository.findAll());
    }


}
