package com.muyan.pms.entity.mapper;

import com.muyan.pms.entity.dto.MenuDto;
import com.muyan.pms.entity.security.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: MenuMapper
 * @description:
 * @author: muyan
 * @date: 2023-03-30 21:57
 **/
@Mapper(componentModel  = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDto, Menu> {
}
