package com.example.jpacrud.config;

import com.example.jpacrud.config.annotation.InfoUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserInfoResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isUserInfoAnnotation = parameter.getParameterAnnotation(InfoUser.class) != null;
        boolean isUserInfoClass = parameter.getParameterType().equals(com.example.jpacrud.dto.auth.UserInfo.class);

        return isUserInfoAnnotation && isUserInfoClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return new com.example.jpacrud.dto.auth.UserInfo(request);
    }
}
