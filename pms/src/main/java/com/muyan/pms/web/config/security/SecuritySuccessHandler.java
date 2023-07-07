package com.muyan.pms.web.config.security;

import com.muyan.pms.cache.TokenCache;
import com.muyan.pms.entity.rest.Result;
import com.muyan.pms.entity.security.JwtToken;
import com.muyan.pms.entity.security.User;
import com.muyan.pms.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: AuthenticationSuccessHandler
 * @description: 登陆成功拦截器
 * @author: muyan
 * @date: 2023/03/23 20:05:56
 **/
@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenCache tokenCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtToken jwtToken = tokenCache.add(principal);
        ResponseUtil.out(response, Result.success(jwtToken));
    }
}
