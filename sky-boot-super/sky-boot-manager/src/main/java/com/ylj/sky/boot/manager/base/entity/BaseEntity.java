package com.ylj.sky.boot.manager.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "自增主键",hidden = true)
    private Long id;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CreatedDate
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date gmtCreate ;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @LastModifiedDate
    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date gmtModified;
    /**
     * 逻辑删除
     */
    @Column(name = "is_deleted")
    @ApiModelProperty(value = "逻辑删除 1 {true} 已删除;0 {false} 未删除",example = "0")
    private boolean deleted = false;
}
