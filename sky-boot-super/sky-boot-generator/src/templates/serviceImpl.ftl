package ${entityPath}.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.factory.PageFactory;
import ${entityPath}.query.${entity}DynamicQueryFactory;
import ${entityPath}.entity.${entity};
import ${entityPath}.repository.${entity}Repository;
import ${entityPath}.service.I${entity}Service;
import ${entityPath}.vo.${entity}Search;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class ${entity}ServiceImpl implements I${entity}Service {
        @Resource
        private ${entity}Repository ${entity?lower_case}Repository;
        @Resource
        private JPAQueryFactory queryFactory;
        /**
        * 新增${entityName}
        */
        @Override
        public R add${entity}(${entity} ${entity?lower_case}) {
            ${entity?lower_case}Repository.save(${entity?lower_case});
            return R.setResult(ResultCodeEnum.ADD_SUCCESS);
        }
        /**
        * 删除${entityName}
        */
        @Override
        @Transactional
        public R del${entity}ById(Long id) {
        ${entity?lower_case}Repository.deleteById(id);
        return R.setResult(ResultCodeEnum.DEL_SUCCESS);
        }
        /**
        * 修改${entityName}
        */
        @Override
        public R edit${entity}(${entity} ${entity?lower_case}) {
            ${entity} one = ${entity?lower_case}Repository.getOne(${entity?lower_case}.getId());
            ${entity?lower_case}.setGmtCreate(one.getGmtCreate());
            ${entity?lower_case}Repository.save(${entity?lower_case});
            return R.setResult(ResultCodeEnum.EDIT_SUCCESS);
        }
        /**
        * 获取${entityName}列表
        */
        @Override
        @Transactional
        public R get${entity}Data(${entity}Search ${entity?lower_case}Search) {
            /*Q${entity} q${entity} = Q${entity}.${entity?lower_case};
            QueryResults<${entity}> ${entity?lower_case}QueryResults = queryFactory.selectFrom(q${entity}).where(${entity}DynamicQueryFactory.createDynamicQuery(${entity?lower_case}Search)).limit(${entity?lower_case}Search.getPageSize()).offset((${entity?lower_case}Search.getCurrentPage() - 1)*${entity?lower_case}Search.getPageSize()).fetchResults();
            return R.ok().data(PageFactory.createPageMap(${entity?lower_case}QueryResults));*/
            return null;
        }


}
