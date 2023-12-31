package com.muyan.pms;

import com.muyan.pms.entity.security.Role;
import com.muyan.pms.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: RoleTest
 * @description:
 * @author: muyan
 * @date: 2023/04/07 16:12:04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testCreate(){
        Role role=new Role();
        role.setName("测试角色");
        role.setRemark("我只是简单的做下测试");
        roleService.create(role);

    }
}
