package com.ylj.sky.boot.manager.user;

import com.ylj.sky.boot.common.vo.R;
import com.ylj.sky.boot.manager.SkyBootManagerApplication;
import com.ylj.sky.boot.manager.model.role.entity.Role;
import com.ylj.sky.boot.manager.model.role.repository.RoleRepository;
import com.ylj.sky.boot.manager.model.user.entity.User;
import com.ylj.sky.boot.manager.model.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SkyBootManagerApplication.class})
@ActiveProfiles("dev")
public class UserTest {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;


    @Test
    public void userAdd(){
        User user = new User();
        user.setAccount("kenan");
//        user.setCname("柯南");
        Role role =new Role();
        role.setRoleCode("ROLE_SUPER_ADMIN");
        role.setRoleName("超级管理员");
        user.getRoles().add(role);

        userRepository.save(user);
        userRepository.flush();
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void mergeUserRoles1(){
        Optional<User> user = userRepository.findById(10L);
        List<Role> roles = user.get().getRoles();
        roles.clear();
        Role role = new Role();
        role.setRoleName("role_add");
        role.setRoleName("角色管理_新增");
        user.get().getRoles().add(role);
        userRepository.save(user.get());
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void mergeUserRoles2(){
        Optional<User> user = userRepository.findById(10L);
        Optional<Role> roleOptional = roleRepository.findById(3L);
        user.get().getRoles().add(roleOptional.get());
        userRepository.save(user.get());
    }
    @Test
    public void generatePassword(){
        String encode = new BCryptPasswordEncoder().encode("111111");
        System.out.println(encode);
    }
    @Test
    public void test3(){
        boolean matches = new BCryptPasswordEncoder().matches("111111", "$2a$10$J/2VvNBV6y11nUUphf6CiubE3TvKsLYX9QLSqbI8Ui/2ZVh2UyRX2");
        System.out.println(matches);
    }
}
