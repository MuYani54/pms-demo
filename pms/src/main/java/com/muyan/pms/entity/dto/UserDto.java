package com.muyan.pms.entity.dto;

import com.muyan.pms.entity.enums.UseStatus;
import lombok.Data;

/**
 * @program: UserDto
 * @description:
 * @author: muyan
 * @date: 2023-04-05 15:40
 **/
@Data
public class UserDto {

    private Long id;

    private String username;
//    private String password;

    private String email;

    private String phone;

    private String realName;

    private RoleDto role;

    private UseStatus useStatus;

}
