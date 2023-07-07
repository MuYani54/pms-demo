package com.muyan.pms.service.impl;

import com.muyan.pms.dao.RoleRepository;
import com.muyan.pms.dao.UserRepository;
import com.muyan.pms.entity.dto.RoleDto;
import com.muyan.pms.entity.mapper.RoleMapper;
import com.muyan.pms.entity.security.Menu;
import com.muyan.pms.entity.security.Role;
import com.muyan.pms.entity.security.User;
import com.muyan.pms.exception.SystemErrorException;
import com.muyan.pms.service.MenuService;
import com.muyan.pms.service.RoleService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.SetUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: RoleServiceImpl
 * @description:
 * @author: muyan
 * @date: 2023-04-05 11:10
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuService menuService;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role create(Role role) {
        if(roleRepository.findRoleByName(role.getName()).isPresent()){
            throw new SystemErrorException("角色名称已存在");
        }
        return roleRepository.save(role);
    }

    @Override
    public void update(Role role) {
        roleRepository.findRoleByName(role.getName()).ifPresent(dbRole ->{
           if(!dbRole.getId().equals(role.getId())){
               throw new SystemErrorException("角色名称已存在");
           }
        });
        roleRepository.findById(role.getId()).ifPresent(roles ->{
            roles.setName(role.getName());
            roles.setRemark(role.getRemark());
            roles.setMenus(role.getMenus());
            roleRepository.save(roles);
        });
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleDto> findAll() {
//        List<String> properties = new ArrayList<>(Arrays.asList("id", "name"));
//        Sort sort = Sort.by(Sort.Direction.DESC, properties);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        Sort sort=Sort.by(Sort.Direction.DESC, Collections.singletonList("id"));
        List<RoleDto> list = roleMapper.toDto(roleRepository.findAll(sort));
        Map<Long, Integer> map = getCiteNum(list);
        list.forEach(role->{
            role.setCiteNum(Objects.isNull(map.get(role.getId()))?0:map.get(role.getId()));
        });
        return list;
    }

    @Override
    public int batchDelete(Set<Long> ids) {
        if(SetUtils.isEmpty(ids)){
            throw new SystemErrorException("ids 不能为空");
        }
        return  roleRepository.deleteRoleByIdIn(ids)==ids.size()?ids.size():0;
    }

    @Override
    public List<RoleDto> search(String name) {
//        Sort sort=Sort.by(Sort.Direction.DESC, Collections.singletonList("id"));
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<RoleDto> list = roleMapper.toDto(roleRepository.findRoleByNameLike("%" + name + "%",sort));
        Map<Long, Integer> map = getCiteNum(list);
        list.forEach(roles->{
            roles.setCiteNum(Objects.isNull(map.get(roles.getId()))?0:map.get(roles.getId()));
        });
        return list;
    }

    /**
     * 获取当前角色的用户应用数
     * @return
     */
    private Map<Long, Integer> getCiteNum(List<RoleDto> list){
        List<User> userList = userRepository.findAll();
        Map<Long,Integer> map= Maps.newHashMapWithExpectedSize(list.size());
        userList.forEach(user->{
            Long id=user.getRole().getId();
            Integer num = map.get(id);
            map.put(id, num == null ? 1 : num + 1);
        });
        return map;
    }

    @Override
    public Role findOne(Long id) {
        if(Objects.isNull(id)){
            throw new SystemErrorException("id 不能为空");
        }
        Role role = roleRepository.findById(id).orElseThrow(() ->new SystemErrorException("用户不存在"));
        if(!SetUtils.isEmpty(role.getMenus())){
            Set<Menu> menus = role.getMenus().stream().filter(item -> item.getPid() != 0L).collect(Collectors.toSet());
            role.setMenus(menus);
        }
        return role;
    }
}
