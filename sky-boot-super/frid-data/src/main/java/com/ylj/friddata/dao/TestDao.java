package com.ylj.friddata.dao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao {

    int testSelect();
}
