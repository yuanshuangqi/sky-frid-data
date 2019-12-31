package com.ylj.sky.boot.manager.frid.service.datamanagement.impl;

import com.ylj.sky.boot.manager.frid.controller.datamanagement.ProductionRequest;
import com.ylj.sky.boot.manager.frid.dao.DataManagementDao;
import com.ylj.sky.boot.manager.frid.model.FridDataInfo;
import com.ylj.sky.boot.manager.frid.model.ProductionInfo;
import com.ylj.sky.boot.manager.frid.service.datamanagement.DataManagementService;
import com.ylj.sky.boot.manager.frid.service.datamanagement.FridDataOutput;
import com.ylj.sky.boot.manager.frid.service.datamanagement.ProductionOutput;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataManagementServiceImpl implements DataManagementService{
    @Resource
    DataManagementDao dataManagementDao;
    @Override
    public FridDataOutput getFridData() {
        List<FridDataInfo> fridDataList =  new ArrayList<FridDataInfo>();
        FridDataInfo taggingInfo = new FridDataInfo();
        taggingInfo.setProcessId("001");
        taggingInfo.setProcessName("吊牌");
        taggingInfo.setTotalCount(dataManagementDao.getTaggingTotal());
        fridDataList.add(taggingInfo);
        FridDataInfo baggingInfo = new FridDataInfo();
        baggingInfo.setProcessId("002");
        baggingInfo.setProcessName("包装");
        baggingInfo.setTotalCount(dataManagementDao.getBaggingTotal());
        fridDataList.add(baggingInfo);
        FridDataInfo metalCheckTotal = new FridDataInfo();
        metalCheckTotal.setProcessId("003");
        metalCheckTotal.setProcessName("检针");
        metalCheckTotal.setTotalCount(dataManagementDao.getMetalCheckTotal());
        fridDataList.add(metalCheckTotal);
        FridDataOutput fridDataOutput = new FridDataOutput();
        fridDataOutput.setFridDataInfoList(fridDataList);
        return fridDataOutput;
    }
    /**get设备产量*/
    public ProductionOutput getProduction(ProductionRequest productionRequest){
        List<ProductionInfo> productionInfos = dataManagementDao.getProduction(productionRequest);
        ProductionOutput productionOutput = new ProductionOutput();
        productionOutput.setProductionInfoList(productionInfos);
        return productionOutput;
    }
}
