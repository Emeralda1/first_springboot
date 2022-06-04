package com.example.lsc.control;

import com.example.lsc.method.interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new interceptor()).addPathPatterns("/**").excludePathPatterns("/index/login").
                excludePathPatterns("/index/register").excludePathPatterns("/index/logincheck").excludePathPatterns("/index/signupcheck").
                excludePathPatterns("/static/**");
    }

}
