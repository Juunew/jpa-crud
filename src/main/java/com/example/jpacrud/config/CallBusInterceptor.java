package com.example.jpacrud.config;

import com.example.jpacrud.constant.errorType.UserError;
import com.example.jpacrud.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class CallBusInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request method = {}", request.getMethod());
        if (Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT") || Objects.equals(request.getMethod(), "DELETE")) {
            return authenticationFilter(request);
        }

        return true;
    }

    private boolean authenticationFilter(HttpServletRequest request) throws UserException {
        log.info("request Authentication = {}", request.getHeader("Authentication"));
        if ("".equals(request.getHeader("Authentication"))) {
             throw new UserException(UserError.UNAUTHORIZED_USER);
        }

        return true;
    }
}
