package com.ylj.friddata.controller;

import com.ylj.friddata.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestController {
    @Resource
    TestService testService;


    @ResponseBody
    @RequestMapping(value = "/hello")
    public String test(){
        int result = testService.testSelect();
        return "nihao"+result;
    }
}
