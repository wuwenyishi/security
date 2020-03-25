package com.haha.xixi.exception;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author admin
 * @data 2018年11月4日 下午5:57:19
 * @desc 类描述
 * <li>拦截401错误：401是 未授权：登录失败
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException {
        try {
            int status = 401;
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String method = request.getMethod();
            String url = request.getRequestURL().toString();
            String msg = authException.getMessage();
            if (StringUtils.startsWith(msg, "Access"))
                msg = "访问令牌Token过期";
            else if (StringUtils.startsWith(msg, "Full"))
                msg = "访问资源需要授权,请检查Token是否存在";
            ErrorJson json = new ErrorJson().build(status, method, url, msg);
            out.print(new Gson().toJson(json));
            out.close();
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
