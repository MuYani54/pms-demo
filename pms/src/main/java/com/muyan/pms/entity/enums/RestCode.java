package com.muyan.pms.entity.enums;

import lombok.Getter;

/**
 * @program: RestCode
 * @description: 全局错误码
 * @author: muyan
 * @date: 2023/03/17 09:00:51
 **/
@Getter
public enum RestCode {
    SUCCESS(200,"SUCCESS"),
    TOKEN_EXPIRE(401,"Token 过期,请重新登陆"),
    SYS_ERROR_EXCEPTION(500,"系统异常,请联系管理员"),;

    private Integer code;

    private String message;

    RestCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
