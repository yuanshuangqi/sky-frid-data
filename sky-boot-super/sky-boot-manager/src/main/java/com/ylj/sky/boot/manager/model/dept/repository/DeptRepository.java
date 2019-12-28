package com.ylj.sky.boot.manager.model.dept.repository;

import com.ylj.sky.boot.manager.model.dept.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface DeptRepository extends JpaRepository<Dept,Long>, QuerydslPredicateExecutor<Dept> {
    List<Dept> findByParentIdEquals(Long id);
}
