package com.ylj.sky.boot.manager.frid.dao;

import com.ylj.sky.boot.manager.frid.controller.datamanagement.ProductionRequest;
import com.ylj.sky.boot.manager.frid.model.ProductionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataManagementDao {
    /*get frid Tagging totaldata*/
    int getTaggingTotal();
    /*get frid bagging totaldata*/
    int getBaggingTotal();
    /*get frid MetalCheck totaldata*/
    int getMetalCheckTotal();
    /* get 每台设备产量*/
    List<ProductionInfo> getProduction(ProductionRequest productionRequest);
}
