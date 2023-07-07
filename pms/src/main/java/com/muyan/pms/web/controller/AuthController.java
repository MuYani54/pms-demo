package com.muyan.pms.web.controller;

import com.muyan.pms.cache.TokenCache;
import com.muyan.pms.entity.enums.RestCode;
import com.muyan.pms.entity.rest.Result;
import com.muyan.pms.entity.security.JwtToken;
import com.muyan.pms.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @program: LogoutController
 * @description:
 * @author: muyan
 * @date: 2023-04-20 19:40
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TokenCache tokenCache;

    @PostMapping("/token")
    public Result refreshToken(@RequestBody JwtToken jwtToken){
        JwtToken token = jwtTokenUtil.refreshToken(jwtToken);
        if(Objects.isNull(token)){
            return Result.failure(RestCode.TOKEN_EXPIRE);
        }
        tokenCache.add(token);
        return Result.success(token);
    }
}
