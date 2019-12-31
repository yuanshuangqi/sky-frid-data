package com.ylj.sky.boot.manager.frid.controller.datamanagement;

import com.ylj.sky.boot.manager.frid.service.datamanagement.DataManagementService;
import com.ylj.sky.boot.manager.frid.service.datamanagement.FridDataOutput;
import com.ylj.sky.boot.manager.frid.service.datamanagement.ProductionOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "数据管理相关API")
@RequestMapping(value = "/frid")
public class DataManagementController {
    @Resource
    DataManagementService dataManagementService;
    @GetMapping(value = "/data")
    @ApiOperation(value = "根据日期FRID数据")
    public FridDataResponse getFridData(){
        FridDataResponse fridDataResponse = new FridDataResponse();
        FridDataOutput fridDataOutput = dataManagementService.getFridData();
        if(!ObjectUtils.isEmpty(fridDataOutput)){
            BeanUtils.copyProperties(fridDataOutput,fridDataResponse);
        }
        return fridDataResponse;
    }

    @PostMapping(value = "/production")
    @ApiOperation(value = "根据日期查询设备产量")
    public ProductionResponse getFridData(ProductionRequest productionRequest){
        ProductionResponse productionResponse = new ProductionResponse();
        ProductionOutput productionOutput = dataManagementService.getProduction(productionRequest);
        if(!ObjectUtils.isEmpty(productionOutput)){
            BeanUtils.copyProperties(productionOutput,productionResponse);
        }
        return productionResponse;
    }
}
