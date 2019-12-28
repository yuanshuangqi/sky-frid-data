package com.ylj.sky.boot.manager.model.menu.vo;

import com.ylj.sky.boot.manager.base.entity.BaseSearch;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Setter;

@Data
@ApiModel(value = "菜单分页搜索类")
public class MenuSearch extends BaseSearch {

    private String menuName;

    private String menuCode;

}
