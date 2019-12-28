package com.ylj.sky.boot.manager.model.dept.controller;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.dept.entity.Dept;
import com.ylj.sky.boot.manager.model.dept.service.IDeptService;
import com.ylj.sky.boot.manager.model.dept.vo.DeptSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "部门管理相关API")
@RequestMapping(value = "/dept")
public class DeptController {
    @Resource
    private IDeptService deptService;

    @PostMapping(value = "/add")
    @ApiOperation("添加部门API")
    public R addDept(@RequestBody Dept dept){
        return deptService.addDept(dept);
    }
    @DeleteMapping(value = "/id/{id}")
    @ApiOperation("删除部门API")
    public R delDeptById(@PathVariable("id")Long id){
        return deptService.delDeptById(id);
    }
    @PutMapping(value = "/edit")
    public R editDept(@RequestBody Dept dept){
        return deptService.editDept(dept);
    }
    @PostMapping(value = "/list/search")
    public R getDeptData(@RequestBody(required = false) DeptSearch deptSearch){
        return deptService.getDeptData(deptSearch);
    }
    @GetMapping(value = "/list")
    public R getList(){
        return deptService.getList();
    }
}
