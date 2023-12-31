package com.muyan.pms.service;

import com.muyan.pms.entity.dto.MenuDto;
import com.muyan.pms.entity.security.Menu;

import java.util.Set;

/**
 * @program: MenuService
 * @description:
 * @author: muyan
 * @date: 2023-03-30 21:17
 **/
public interface MenuService {

    /**
     * 查询全部用户列表
     * @return
     */
    Set<MenuDto> findAllByMenuTree();

    /**
     * 查询当前用户菜单列表
     * @return
     */
    Set<MenuDto> getCurrMenuTree();

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    MenuDto create(Menu menu);

    /**
     * 更新菜单
     * @param menu
     */
    void update(Menu menu);

    /**
     * 删除id
     * @param id
     */
    void delete(Long id);



}
