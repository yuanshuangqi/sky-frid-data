package com.ylj.sky.boot.manager.model.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.factory.PageFactory;
import com.ylj.sky.boot.manager.model.dept.entity.Dept;
import com.ylj.sky.boot.manager.model.dept.repository.DeptRepository;
import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.model.role.repository.RoleRepository;
import com.ylj.sky.boot.manager.model.user.entity.QUser;
import com.ylj.sky.boot.manager.model.user.entity.User;
import com.ylj.sky.boot.manager.model.user.query.UserDynamicQueryFactory;
import com.ylj.sky.boot.manager.model.user.repository.UserRepository;
import com.ylj.sky.boot.manager.model.user.service.IUserService;
import com.ylj.sky.boot.manager.model.user.vo.UserRoleAssignVo;
import com.ylj.sky.boot.manager.model.user.vo.UserSearch;
import com.ylj.sky.boot.manager.security.entity.SecurityUser;
import com.ylj.sky.boot.manager.security.factory.SecurityFactory;
import com.ylj.sky.boot.manager.util.menu.MenuUtil;
import com.ylj.sky.boot.manager.util.user.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private DeptRepository deptRepository;
    @Resource
    private JPAQueryFactory queryFactory;
    @Override
    public R addUser(User user) {
        user.setPassword(UserUtil.getEncryptPassword());
        if(user.getDept().getId() != null && user.getDept().getId() != 0L){
            Optional<Dept> deptOptional = deptRepository.findById(user.getDept().getId());
            deptOptional.ifPresent(user::setDept);
        }else {
            user.setDept(null);
        }
        userRepository.save(user);
        return R.setResult(ResultCodeEnum.ADD_SUCCESS);
    }

    @Override
    public R editUser(User user) {
        User one = userRepository.getOne(user.getId());
        if(CollUtil.isEmpty(user.getRoles())){
            user.setRoles(one.getRoles());
        }
        user.setPassword(one.getPassword());
        user.setGmtCreate(one.getGmtCreate());
        userRepository.save(user);
        return R.setResult(ResultCodeEnum.EDIT_SUCCESS);
    }

    @Override
    public R userRoleAssign(UserRoleAssignVo roleAssignVo) {
        Long userId = roleAssignVo.getId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            Long[] roleIds = roleAssignVo.getRoleIds();
            // 删除用户的所有角色信息
            user.getRoles().clear();
            if(roleIds != null && roleIds.length > 0){
                List<Role> roles = roleRepository.findAllByIdIn(roleIds);
                user.setRoles(roles);
            }
            userRepository.save(user);
            return R.setResult(ResultCodeEnum.ROLE_ASSIGN_SUCCESS);
        }
        return R.error();
    }

    @Override
    @Transactional
    public R delUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            List<Role> roles = userOptional.get().getRoles();
            if(CollUtil.isNotEmpty(roles)){
                roles.forEach(role -> role.getUsers().remove(userOptional.get()));
            }
        }
        userRepository.deleteById(id);
        return R.setResult(ResultCodeEnum.DEL_SUCCESS);
    }

    @Override
    @Transactional
    public R getUserData(UserSearch userSearch) {
        QUser qUser = QUser.user;
        QueryResults<User> userQueryResults = queryFactory.selectFrom(qUser).where(UserDynamicQueryFactory.createDynamicQuery(userSearch)).limit(userSearch.getPageSize()).offset((userSearch.getCurrentPage() - 1)*userSearch.getPageSize()).fetchResults();
        return R.ok().data(PageFactory.createPageMap(userQueryResults));
    }

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return R
     */
    @Override
    public R getUserInfoByUsername(String username) {
        User user = userRepository.findByAccountEndingWith(username);
        //密码重置为null
        user.setPassword(null);
        List<Role> roles = user.getRoles();
        if(CollUtil.isEmpty(roles)){ // 用户没有系统的任何角色，那么无法进入系统
            return R.setResult(ResultCodeEnum.USER_NO_PERMISSION);
        }
        //根据用户的角色，生成用户的菜单和按钮权限树,并根据ID去除重复的
        ArrayList<Menu> menus = roles.stream().map(Role::getMenus).flatMap(Collection::stream).collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Menu::getId))), ArrayList::new));
        //获取所有的父级菜单
        List<Menu> parentMenus = menus.stream().filter(e -> e.getParentId() == 0L).collect(Collectors.toList());
        //递归封装子菜单
        for (Menu parentMenu : parentMenus) {
            //此方法为加载用户所具有的菜单权限，故不能加载按钮权限
            parentMenu.setChildren(MenuUtil.getChildrenNoBtn(parentMenu,menus));
        }
        user.setTreeMenus(parentMenus);
        user.setAllMenus(menus);
        return R.ok().data(user);
    }

    @Override
    @Transactional
    public SecurityUser getUserRolesByUsername(String username) {
        User user = userRepository.findByAccountEndingWith(username);
        return  SecurityFactory.create(user);
    }
}
