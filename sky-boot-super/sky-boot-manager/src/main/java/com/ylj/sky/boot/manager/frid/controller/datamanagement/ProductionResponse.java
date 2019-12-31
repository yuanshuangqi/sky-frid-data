package com.ylj.sky.boot.manager.frid.controller.datamanagement;

import com.ylj.sky.boot.manager.frid.model.ProductionInfo;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class ProductionResponse {
    private List<ProductionInfo> productionInfoList;

}
