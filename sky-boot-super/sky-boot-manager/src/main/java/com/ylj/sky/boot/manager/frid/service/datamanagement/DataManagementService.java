package com.ylj.sky.boot.manager.frid.service.datamanagement;

import com.ylj.sky.boot.manager.frid.controller.datamanagement.ProductionRequest;

public interface DataManagementService {
    /**get Frid data*/
    FridDataOutput getFridData();
    ProductionOutput getProduction(ProductionRequest request);
}
