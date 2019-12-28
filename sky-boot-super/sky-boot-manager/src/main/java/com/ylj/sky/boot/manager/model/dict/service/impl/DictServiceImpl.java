package com.ylj.sky.boot.manager.model.dict.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.factory.PageFactory;
import com.ylj.sky.boot.manager.model.dict.entity.QDict;
import com.ylj.sky.boot.manager.model.dict.query.DictDynamicQueryFactory;
import com.ylj.sky.boot.manager.model.dict.entity.Dict;
import com.ylj.sky.boot.manager.model.dict.repository.DictRepository;
import com.ylj.sky.boot.manager.model.dict.service.IDictService;
import com.ylj.sky.boot.manager.model.dict.vo.DictSearch;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DictServiceImpl implements IDictService {
    @Resource
    private DictRepository dictRepository;
    @Resource
    private JPAQueryFactory queryFactory;

    /**
     * 新增字典
     */
    @Override
    public R addDict(Dict dict) {
        dictRepository.save(dict);
        List<Dict> children = dict.getChildren();
        if (CollUtil.isNotEmpty(children)) {
            for (Dict child : children) {
                child.setParentId(dict.getId());
            }
            dictRepository.saveAll(children);
        }
        return R.setResult(ResultCodeEnum.ADD_SUCCESS);
    }

    /**
     * 删除字典
     */
    @Override
    @Transactional
    public R delDictById(Long id) {
        dictRepository.deleteById(id);
        dictRepository.deleteByParentIdEquals(id);
        return R.setResult(ResultCodeEnum.DEL_SUCCESS);
    }

    /**
     * 修改字典
     */
    @Override
    public R editDict(Dict dict) {
        //删除字典
        dictRepository.deleteById(dict.getId());
        //删除子字典
        dictRepository.deleteByParentIdEquals(dict.getId());
        dict.setId(null);
        dictRepository.save(dict);
        //批量增加
        List<Dict> children = dict.getChildren();
        if (CollUtil.isNotEmpty(children)) {
            for (Dict child : children) {
                child.setId(null);
                child.setParentId(dict.getId());
            }
            dictRepository.saveAll(children);
        }
        return R.setResult(ResultCodeEnum.EDIT_SUCCESS);
    }

    /**
     * 获取字典列表
     */
    @Override
    @Transactional
    public R getDictData(DictSearch dictSearch) {
        QDict qDict = QDict.dict;
        QueryResults<Dict> dictQueryResults = queryFactory.selectFrom(qDict).where(DictDynamicQueryFactory.createDynamicQuery(dictSearch)).limit(dictSearch.getPageSize()).offset((dictSearch.getCurrentPage() - 1) * dictSearch.getPageSize()).fetchResults();
        List<Dict> all = dictQueryResults.getResults();
        List<Dict> parentDicts = all.stream().filter(e -> e.getParentId() == 0L).collect(Collectors.toList());
        for (Dict parentDict : parentDicts) {
            parentDict.setChildren(all.stream().filter(e -> e.getParentId().equals(parentDict.getId())).collect(Collectors.toList()));
        }
        return R.ok().data(PageFactory.createPageMap(parentDicts));
    }


}
