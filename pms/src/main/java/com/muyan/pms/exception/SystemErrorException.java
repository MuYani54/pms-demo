package com.muyan.pms.exception;

import com.muyan.pms.entity.enums.RestCode;

import java.util.Optional;

/**
 * @program: SystemErrorException
 * @description: 异常内部异常
 * @author: muyan
 * @date: 2023/03/17 09:01:29
 **/
public class SystemErrorException extends BaseException {

    public SystemErrorException(String message) {
        super(Optional.ofNullable(message).orElse(RestCode.SYS_ERROR_EXCEPTION.getMessage()));
        this.code = RestCode.SYS_ERROR_EXCEPTION.getCode();
    }
}
