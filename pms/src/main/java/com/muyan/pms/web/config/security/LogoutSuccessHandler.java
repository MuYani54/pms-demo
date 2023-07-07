package com.muyan.pms.web.config.security;

import com.muyan.pms.cache.TokenCache;
import com.muyan.pms.entity.rest.Result;
import com.muyan.pms.entity.security.User;
import com.muyan.pms.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: LogoutSuccessHandler
 * @description: 登出成功Handler
 * @author: muyan
 * @date: 2023-04-20 19:01
 **/
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Autowired
    private TokenCache tokenCache;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User details = (User) authentication.getPrincipal();
        tokenCache.remove(details.getUsername());
        ResponseUtil.out(response, Result.success("恭喜你成功退出登陆"));
    }
}
