package com.ylj.sky.boot.manager.frid.service.datamanagement;

import com.ylj.sky.boot.manager.frid.model.FridDataInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class FridDataOutput implements Serializable{
   List<FridDataInfo> fridDataInfoList;
}
