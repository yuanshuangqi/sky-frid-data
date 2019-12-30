package com.ylj.sky.boot.manager.frid.service.impl;


import com.ylj.sky.boot.manager.frid.dao.TestDao;
import com.ylj.sky.boot.manager.frid.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService{
    @Resource
    TestDao testDao;
    @Override
    public int testSelect(){
       int result = testDao.testSelect();
        return result;
    }
}
