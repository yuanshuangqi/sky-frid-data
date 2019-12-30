package com.ylj.sky.boot.manager.frid.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataManagementDao {
    /*get frid Tagging totaldata*/
    int getTaggingTotal();
    /*get frid bagging totaldata*/
    int getBaggingTotal();
    /*get frid MetalCheck totaldata*/
    int getMetalCheckTotal();
}
