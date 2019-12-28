package com.ylj.sky.boot.manager.model.dict.query;

import cn.hutool.core.util.StrUtil;
import com.querydsl.core.BooleanBuilder;
import com.ylj.sky.boot.manager.model.dict.entity.QDict;
import com.ylj.sky.boot.manager.model.dict.vo.DictSearch;


public class DictDynamicQueryFactory {
    public static BooleanBuilder createDynamicQuery(DictSearch dictSearch) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (dictSearch != null) {
            QDict qDict = QDict.dict;
            if (StrUtil.isNotBlank(dictSearch.getDictName())) {
                booleanBuilder.and(qDict.dictName.eq(dictSearch.getDictName()));
            }
        }
        return booleanBuilder;
    }
}
