package ${entityPath}.query;

import com.querydsl.core.BooleanBuilder;
import ${entityPath}.vo.DictSearch;


public class ${entity}DynamicQueryFactory {
        public static BooleanBuilder createDynamicQuery(${entity}Search ${entity?lower_case}Search){
                BooleanBuilder booleanBuilder = new BooleanBuilder();
                if(${entity?lower_case}Search != null){
                }
                return booleanBuilder;
        }
}
