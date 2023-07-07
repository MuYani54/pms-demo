package com.muyan.pms.web.config.security;

import com.muyan.pms.entity.enums.RestCode;
import com.muyan.pms.entity.rest.Result;
import com.muyan.pms.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: LoginUrlAuthentication
 * @description:
 * @author: muyan
 * @date: 2023/03/25 00:25:30
 **/
public class LoginUrlAuthentication extends LoginUrlAuthenticationEntryPoint {

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public LoginUrlAuthentication(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response,Result.failure(RestCode.TOKEN_EXPIRE.getCode(),"非法访问,请先登陆!!!"));
    }
}
