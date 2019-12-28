package com.ylj.sky.boot.manager.security.service;

import com.ylj.sky.boot.common.constants.ResultCodeEnum;
import com.ylj.sky.boot.common.exception.SkyBootException;
import com.ylj.sky.boot.manager.model.user.entity.User;
import com.ylj.sky.boot.manager.model.user.repository.UserRepository;
import com.ylj.sky.boot.manager.model.user.service.IUserService;
import com.ylj.sky.boot.manager.security.factory.SecurityFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements UserDetailsService {
    @Resource
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userRepository.findByAccountEndingWith(account);
        if(user == null){
            throw new SkyBootException(ResultCodeEnum.ACCOUNT_UNKNOWN);
        }
        return SecurityFactory.login(user);
    }
}
