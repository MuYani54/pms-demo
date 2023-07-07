package com.muyan.pms;

import com.muyan.pms.dao.UserRepository;
import com.muyan.pms.entity.security.Role;
import com.muyan.pms.entity.security.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: UserPwdTest
 * @description:
 * @author: muyan
 * @date: 2023/03/17 13:01:10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPwdTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encodeTest(){
        System.out.println(passwordEncoder.encode("admin"));
    }

    @Test
    //@Transactional
    public void addUser(){
        Role role=new Role();
        role.setId(1L);
        role.setName("管理员");
        role.setDeleteFlag(0);

        User user=new User();
        user.setUsername("admin22");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRealName("张三");

        user.setDeleteFlag(0);
        user.setRole(role);
        userRepository.save(user);
    }

    @Test
    public void findUser(){
        userRepository.findByUsername("admin").ifPresent(System.out::println);
    }

    @Test
    @Transactional
    public void lock(){
        userRepository.lockUser(23L);
    }
 }
