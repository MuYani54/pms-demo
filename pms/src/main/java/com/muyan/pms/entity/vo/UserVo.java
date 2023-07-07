package com.muyan.pms.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: UserVo
 * @description:
 * @author: muyan
 * @date: 2023/04/22 11:06:12
 **/
@Data
public class UserVo {

    @NotNull(message = "用户id不能为空")
    public Long id;

    @NotBlank(message = "旧密码不能为空")
    public String oldPassword;

    @NotBlank(message = "新密码不能为空")
    public String newPassword;

}
