package ${entityPath}.controller;

import com.ylj.sky.boot.common.vo.R;
import ${entityPath}.entity.${entity};
import ${entityPath}.service.I${entity}Service;
import ${entityPath}.vo.${entity}Search;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "${entityName}管理相关API")
@RequestMapping(value = "/${entity?lower_case}")
public class ${entity}Controller {
        @Resource
        private I${entity}Service ${entity?lower_case}Service;

        @PostMapping(value = "/add")
        @ApiOperation("添加${entityName}API")
        public R add${entity}(@RequestBody ${entity} ${entity?lower_case}){
        return ${entity?lower_case}Service.add${entity}(${entity?lower_case});
        }

        @DeleteMapping(value = "/id/{id}")
        @ApiOperation("删除${entityName}API")
        public R del${entity}ById(@PathVariable("id")Long id){
        return ${entity?lower_case}Service.del${entity}ById(id);
        }

        @PutMapping(value = "/edit")
        @ApiOperation("编辑${entityName}API")
        public R edit${entity}(@RequestBody ${entity} ${entity?lower_case}){
        return ${entity?lower_case}Service.edit${entity}(${entity?lower_case});
        }

        @PostMapping(value = "/list/search")
        public R get${entity}Data(@RequestBody ${entity}Search ${entity?lower_case}Search){
        return ${entity?lower_case}Service.get${entity}Data(${entity?lower_case}Search);
        }

}
