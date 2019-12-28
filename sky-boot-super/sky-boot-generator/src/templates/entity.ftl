package ${entityPath}.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ylj.sky.boot.manager.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ApiModel(value = "${entityName}实体类",description = "")
public class ${entity}  extends BaseEntity {

}
