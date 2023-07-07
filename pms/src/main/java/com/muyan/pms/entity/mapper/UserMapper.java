package com.muyan.pms.entity.mapper;

import com.muyan.pms.entity.dto.UserDto;
import com.muyan.pms.entity.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @program: UserMapper
 * @description:
 * @author: muyan
 * @date: 2023-04-05 15:45
 **/
@Mapper(componentModel  = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDto, User> {
}
