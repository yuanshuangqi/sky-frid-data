package com.ylj.sky.boot.manager.model.dept.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.factory.PageFactory;
import com.ylj.sky.boot.manager.model.dept.entity.Dept;
import com.ylj.sky.boot.manager.model.dept.entity.QDept;
import com.ylj.sky.boot.manager.model.dept.query.DeptDynamicQueryFactory;
import com.ylj.sky.boot.manager.model.dept.repository.DeptRepository;
import com.ylj.sky.boot.manager.model.dept.service.IDeptService;
import com.ylj.sky.boot.manager.model.dept.vo.DeptSearch;
import com.ylj.sky.boot.manager.model.user.entity.User;
import com.ylj.sky.boot.manager.util.dept.DeptUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl implements IDeptService {
    @Resource
    private DeptRepository deptRepository;
    @Resource
    private JPAQueryFactory queryFactory;
    @Override
    public R addDept(Dept dept) {
        deptRepository.save(dept);
        return R.setResult(ResultCodeEnum.ADD_SUCCESS);
    }

    @Override
    @Transactional
    public R delDeptById(Long id) {
        Dept dept = deptRepository.getOne(id);
        Set<User> users = dept.getUsers();
        users.forEach(user -> user.setDept(null));
        deptRepository.deleteById(id);
        return R.setResult(ResultCodeEnum.DEL_SUCCESS);
    }

    @Override
    public R editDept(Dept dept) {
        //父级菜单不能是自身
        if(dept.getId().equals(dept.getParentId())){
            return R.setResult(ResultCodeEnum.DEPT_NO_SELF);
        }
        List<Dept> children = deptRepository.findByParentIdEquals(dept.getId());
        if(CollUtil.isNotEmpty(children)){
            //父级菜单不能是子菜单
            if(children.stream().anyMatch(e->e.getId().equals(dept.getParentId()))){
                return R.setResult(ResultCodeEnum.DEPT_NO_CHILDREN);
            }
        }
        deptRepository.save(dept);
        return R.setResult(ResultCodeEnum.EDIT_SUCCESS);
    }

    @Override
    public R getDeptData(DeptSearch deptSearch) {
        QDept qDept = QDept.dept;
        QueryResults<Dept> deptQueryResults = queryFactory.selectFrom(qDept).where(DeptDynamicQueryFactory.createDynamicQuery(deptSearch)).fetchResults();
        //获取所有菜单
        List<Dept> allDepts = deptQueryResults.getResults();
        //获取所有父级菜单
        List<Dept> parentDepts = allDepts.stream().filter(e -> e.getParentId() == 0L).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(parentDepts)){
            //递归封装子角色
            for (Dept dept : parentDepts) {
                dept.setChildren(DeptUtil.getChildren(dept,allDepts));
            }
            return R.ok().data(PageFactory.createPageMap(parentDepts));
        }else {
            return R.ok().data(PageFactory.createPageMap(allDepts));
        }

    }
    @Override
    public R getList() {
        return R.ok().data(deptRepository.findAll());
    }
}
