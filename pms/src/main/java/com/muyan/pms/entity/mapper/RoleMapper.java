package com.muyan.pms.entity.mapper;

import com.muyan.pms.entity.dto.RoleDto;
import com.muyan.pms.entity.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: RoleMapper
 * @description:
 * @author: muyan
 * @date: 2023/04/04 15:40:47
 **/
@Mapper(componentModel  = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
}
