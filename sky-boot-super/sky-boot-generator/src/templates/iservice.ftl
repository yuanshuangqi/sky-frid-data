package ${entityPath}.service;

import com.ylj.sky.boot.common.vo.R;
import ${entityPath}.entity.${entity};
import ${entityPath}.vo.${entity}Search;

public interface I${entity}Service {
        /**
        * 新增${entityName}
        */
        R add${entity}(${entity} ${entity?lower_case});
        /**
        * 删除${entityName}
        */
        R del${entity}ById(Long id);
        /**
        * 修改${entityName}
        */
        R edit${entity}(${entity} ${entity?lower_case});
        /**
        * 获取${entityName}列表
        */
        R get${entity}Data(${entity}Search ${entity?lower_case}Search);
        
       
        
        
}
