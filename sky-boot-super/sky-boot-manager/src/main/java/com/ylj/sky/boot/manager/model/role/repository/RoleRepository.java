package com.ylj.sky.boot.manager.model.role.repository;

import com.ylj.sky.boot.manager.model.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long>, QuerydslPredicateExecutor<Role> {
    /**
     * 通过角色code 获取角色信息
     * @param roleCode
     * @return
     */
    Role findByRoleCodeEquals(String roleCode);

    /**
     * 获取某个角色下的所有子角色
     * @param parentId 父角色Id
     * @return 子角色集合
     */
    List<Role> findByParentIdEquals(Long parentId);

    List<Role> findAllByIdIn(Long[] ids);

    void deleteByParentIdEquals(Long parentId);
}
