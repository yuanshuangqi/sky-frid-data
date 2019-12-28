package com.ylj.sky.boot.manager.model.dict.controller;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.model.dict.entity.Dict;
import com.ylj.sky.boot.manager.model.dict.service.IDictService;
import com.ylj.sky.boot.manager.model.dict.vo.DictSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "字典管理相关API")
@RequestMapping(value = "/dict")
public class DictController {
        @Resource
        private IDictService dictService;

        @PostMapping(value = "/add")
        @ApiOperation("添加字典API")
        public R addDict(@RequestBody Dict dict){
        return dictService.addDict(dict);
        }

        @DeleteMapping(value = "/id/{id}")
        @ApiOperation("删除字典API")
        public R delDictById(@PathVariable("id")Long id){
        return dictService.delDictById(id);
        }

        @PutMapping(value = "/edit")
        @ApiOperation("编辑字典API")
        public R editDict(@RequestBody Dict dict){
        return dictService.editDict(dict);
        }

        @PostMapping(value = "/list/search")
        public R getDictData(@RequestBody DictSearch dictSearch){
        return dictService.getDictData(dictSearch);
        }

}
