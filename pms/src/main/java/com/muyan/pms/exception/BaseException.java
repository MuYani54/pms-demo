package com.muyan.pms.exception;

import com.muyan.pms.entity.enums.RestCode;
import lombok.Getter;

/**
 * @program: BaseException
 * @description:
 * @author: muyan
 * @date: 2023/03/23 18:53:36
 **/
@Getter
public class BaseException extends RuntimeException{
    protected Integer code;

    BaseException(String message) {
      super(message);
    }

    BaseException(RestCode restCode){
        super(restCode.getMessage());
        this.code = restCode.getCode();
    }

}
