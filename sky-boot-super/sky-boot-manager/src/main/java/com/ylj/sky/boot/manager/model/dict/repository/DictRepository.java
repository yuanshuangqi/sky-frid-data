package com.ylj.sky.boot.manager.model.dict.repository;

import com.querydsl.core.types.Predicate;
import com.ylj.sky.boot.manager.model.dict.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface DictRepository extends JpaRepository<Dict,Long> , QuerydslPredicateExecutor<Dict> {

    /**
     * 删除子字典
     */
    void deleteByParentIdEquals(Long parentId);
}
