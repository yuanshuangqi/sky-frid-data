package com.ylj.sky.boot.manager;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ylj.sky.boot.manager.security.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing //开启jpa 自定填充
@EnableCaching
@ComponentScan(basePackages = {"com.ylj.sky.boot.manager","com.ylj.sky.boot.common"})
public class SkyBootManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyBootManagerApplication.class,args);
    }
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}
