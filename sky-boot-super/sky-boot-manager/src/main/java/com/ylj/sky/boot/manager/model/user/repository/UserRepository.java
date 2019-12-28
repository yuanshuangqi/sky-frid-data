package com.ylj.sky.boot.manager.model.user.repository;

import com.querydsl.core.types.Predicate;
import com.ylj.sky.boot.manager.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> , QuerydslPredicateExecutor<User> {
        /**
         * 根据账号获取用户信息
         * @param account 用户账号
         */
        User findByAccountEndingWith(String account);

        List<User> findAll(Predicate p);

}
