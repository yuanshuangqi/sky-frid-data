package com.ylj.sky.boot.manager.frid.service.datamanagement.impl;

import com.ylj.sky.boot.manager.frid.dao.DataManagementDao;
import com.ylj.sky.boot.manager.frid.model.FridDataInfo;
import com.ylj.sky.boot.manager.frid.service.datamanagement.DataManagementService;
import com.ylj.sky.boot.manager.frid.service.datamanagement.FridDataOutput;
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
        fridDataList.add(taggingInfo);
        FridDataInfo metalCheckTotal = new FridDataInfo();
        metalCheckTotal.setProcessId("003");
        metalCheckTotal.setProcessName("检针");
        metalCheckTotal.setTotalCount(dataManagementDao.getMetalCheckTotal());
        fridDataList.add(metalCheckTotal);
        FridDataOutput fridDataOutput = new FridDataOutput();
        fridDataOutput.setFridDataInfoList(fridDataList);
        return fridDataOutput;
    }
}
