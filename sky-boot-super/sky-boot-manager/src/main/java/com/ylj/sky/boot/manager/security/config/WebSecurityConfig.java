package com.ylj.sky.boot.manager.security.config;

import com.ylj.sky.boot.manager.model.user.repository.UserRepository;
import com.ylj.sky.boot.manager.model.user.service.IUserService;
import com.ylj.sky.boot.manager.security.filter.JwtLoginFilter;
import com.ylj.sky.boot.manager.security.filter.JwtVerifyFilter;
import com.ylj.sky.boot.manager.security.service.SecurityServiceImpl;
import com.ylj.sky.boot.manager.util.user.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private IUserService userService;
    @Resource
    private RsaKeyProperties rsaKeyProperties;
    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(new PasswordEncoder(){
                    public String encode(CharSequence rawPassword) {
                        return UserUtil.getEncryptPassword((String)rawPassword);
                     }
                     public boolean matches(CharSequence rawPassword, String encodedPassword) {//rawPassword用户输入的，encodedPassword数据库查出来的
                        return encodedPassword.equals(UserUtil.getEncryptPassword((String)rawPassword));
                    }}
                );
    }
    // 装载BCrypt密码编码器
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                csrf().disable()
                //采用JWT,所以不需要Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagge‌​r-ui.html").permitAll()
                // 禁用其余所有请求
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtLoginFilter(super.authenticationManager(),rsaKeyProperties))
                .addFilter(new JwtVerifyFilter(super.authenticationManager(),rsaKeyProperties,userService));

        //禁用缓存
        httpSecurity.headers().cacheControl();
    }

}
