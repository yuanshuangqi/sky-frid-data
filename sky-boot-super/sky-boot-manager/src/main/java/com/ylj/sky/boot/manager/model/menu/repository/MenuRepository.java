package com.ylj.sky.boot.manager.model.menu.repository;

import com.ylj.sky.boot.manager.model.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long>, QuerydslPredicateExecutor<Menu> {
    List<Menu> findByParentIdEquals(Long parentId);
    List<Menu> findAllByIdIn(Long[] ids);
    void deleteByParentIdEquals(Long parentId);
}
