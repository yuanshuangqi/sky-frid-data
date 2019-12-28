package ${entityPath}.repository;

import com.querydsl.core.types.Predicate;
import ${entityPath}.entity.${entity};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ${entity}Repository extends JpaRepository<${entity},Long> , QuerydslPredicateExecutor<${entity}> {
}
