package com.ylj.sky.boot.manager.model.role.controler;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.model.role.service.IRoleService;
import com.ylj.sky.boot.manager.model.role.vo.RoleMenuAssignVo;
import com.ylj.sky.boot.manager.model.role.vo.RoleSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "角色管理相关API")
@RequestMapping(value = "/role")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @PostMapping(value = "/add")
    @ApiOperation("添加角色API")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') && hasAuthority('role_mgr_add')")
    public R addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }
    @DeleteMapping(value = "/id/{id}")
    @ApiOperation("删除角色API")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') && hasAuthority('role_mgr_del')")
    public R delRoleById(@PathVariable("id")Long id){
        return roleService.delRoleById(id);
    }
    @PutMapping(value = "/edit")
    @ApiOperation("编辑角色API")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') && hasAuthority('role_mgr_edit')")
    public R editRole(@RequestBody Role role){
        return roleService.editRole(role);
    }
    @PutMapping(value = "menuAssign")
    @ApiOperation("权限分配API")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') && hasAuthority('role_mgr_menu_assign')")
    public R menuAssign(@RequestBody RoleMenuAssignVo roleMenuAssignVo){
        return roleService.menuAssign(roleMenuAssignVo);
    }
    @PostMapping(value = "/list/search")
    public R getRoleData(@RequestBody(required = false) RoleSearch roleSearch){
        return roleService.getRoleData(roleSearch);
    }
    @GetMapping(value = "/list")
    public R getList(){
        return roleService.getList();
    }
}
