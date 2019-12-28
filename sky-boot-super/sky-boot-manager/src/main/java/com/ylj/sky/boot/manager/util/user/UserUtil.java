package com.ylj.sky.boot.manager.util.user;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtil {
    /**
     * 获得加密后的密码
     * @param password 密码
     * @return 加密后的密码
     */
    public static String getEncryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
    public static String getEncryptPassword(){
        return new BCryptPasswordEncoder().encode("111111");
    }
}
