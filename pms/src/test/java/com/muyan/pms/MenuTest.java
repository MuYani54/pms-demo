package com.muyan.pms;

import com.muyan.pms.entity.dto.MenuDto;
import com.muyan.pms.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @program: MenuTest
 * @description:
 * @author: muyan
 * @date: 2023/04/06 14:44:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testMenuTree(){
        Set<MenuDto> menuTree = menuService.findAllByMenuTree();
        System.out.println(menuTree);
    }
}
