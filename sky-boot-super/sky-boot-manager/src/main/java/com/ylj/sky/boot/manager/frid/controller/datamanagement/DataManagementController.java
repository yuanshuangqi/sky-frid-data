package com.ylj.sky.boot.manager.frid.controller.datamanagement;

import com.ylj.sky.boot.manager.frid.service.datamanagement.DataManagementService;
import com.ylj.sky.boot.manager.frid.service.datamanagement.FridDataOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "数据管理相关API")
@RequestMapping(value = "/frid")
public class DataManagementController {
    @Resource
    DataManagementService dataManagementService;
    @GetMapping(value = "/data")
    @ApiOperation(value = "根据用户登录账号查询用户API")
    public FridDataResponse getFridData(){
        FridDataResponse fridDataResponse = new FridDataResponse();
        FridDataOutput fridDataOutput = dataManagementService.getFridData();
        if(!ObjectUtils.isEmpty(fridDataOutput)){
            BeanUtils.copyProperties(fridDataOutput,fridDataResponse);
        }
        return fridDataResponse;
    }
}
