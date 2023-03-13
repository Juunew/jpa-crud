package com.example.jpacrud.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserInfoResolver userInfoResolver;

    private final List<String> includesPath = Arrays.asList("/api/v1/posts/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CallBusInterceptor())
                .addPathPatterns(includesPath);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userInfoResolver);
    }

}
