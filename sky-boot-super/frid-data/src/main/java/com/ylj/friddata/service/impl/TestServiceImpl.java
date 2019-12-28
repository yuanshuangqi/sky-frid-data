package com.ylj.friddata.service.impl;

import com.ylj.friddata.dao.TestDao;
import com.ylj.friddata.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class TestServiceImpl implements TestService{
    @Autowired
    TestDao testDao;
    @Override
    public int testSelect(){
       int result = testDao.testSelect();
        return result;
    }
}
