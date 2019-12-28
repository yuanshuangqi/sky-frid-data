package com.ylj.sky.boot.manager.model.dict.service;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.dict.entity.Dict;
import com.ylj.sky.boot.manager.model.dict.vo.DictSearch;

public interface IDictService {
        /**
        * 新增字典
        */
        R addDict(Dict dict);
        /**
        * 删除字典
        */
        R delDictById(Long id);
        /**
        * 修改字典
        */
        R editDict(Dict dict);
        /**
        * 获取字典列表
        */
        R getDictData(DictSearch dictSearch);
        
       
        
        
}
