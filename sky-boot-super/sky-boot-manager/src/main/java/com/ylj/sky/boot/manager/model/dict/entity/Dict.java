package com.ylj.sky.boot.manager.model.dict.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylj.sky.boot.manager.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ApiModel(value = "字典实体类",description = "")
public class Dict  extends BaseEntity {
    @ApiModelProperty(value = "字典code")
    private String dictCode;
    @ApiModelProperty(value = "字典名称")
    private String dictName;
    @ApiModelProperty(value = "字典编号")
    private Integer dictNum;
    @ApiModelProperty(value = "父字典")
    private Long parentId =0L;

    @Transient
    private List<Dict> children = new ArrayList<>();


}
