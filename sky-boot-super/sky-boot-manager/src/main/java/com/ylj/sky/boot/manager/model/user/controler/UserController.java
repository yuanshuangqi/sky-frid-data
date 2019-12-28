package com.ylj.sky.boot.manager.model.user.controler;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.user.entity.User;
import com.ylj.sky.boot.manager.model.user.service.IUserService;
import com.ylj.sky.boot.manager.model.user.vo.UserRoleAssignVo;
import com.ylj.sky.boot.manager.model.user.vo.UserSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户管理相关API")
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping(value = "/add")
    @ApiOperation("添加用户API")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') && hasAuthority('user_mgr_add') ")
    public R addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping(value = "/id/{id}")
    @ApiOperation("删除用户API")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') && hasAuthority('user_mgr_del') ")
    public R delUserById(@PathVariable("id")Long id){
        return userService.delUserById(id);
    }

    @PutMapping(value = "/edit")
    @ApiOperation("编辑用户API")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') && hasAuthority('user_mgr_edit') ")
    public R editUser(@RequestBody User user){
        return userService.editUser(user);
    }

    @PostMapping(value = "/list")
    @ApiOperation("查询用户API")
    public R getUserData(@RequestBody UserSearch userSearch){
        return userService.getUserData(userSearch);
    }

    @GetMapping(value = "/username/{username}")
    @ApiOperation(value = "根据用户登录账号查询用户API")
    public R getUserInfoByUsername(@PathVariable("username")String username){
        return userService.getUserInfoByUsername(username);
    }

    @PutMapping(value = "/roleAssign")
    @ApiOperation(value = "角色分配API")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') && hasAuthority('user_mgr_role_assign') ")
    public R userRoleAssign(@RequestBody UserRoleAssignVo roleAssignVo){
        return userService.userRoleAssign(roleAssignVo);
    }
}
