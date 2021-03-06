package com.example.lsc.control;

import com.example.lsc.method.interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mvcConfiguration implements WebMvcConfigurer {
    //配置类bean 注册拦截器并设定需要拦截的url以及需要排除的url
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptor interceptor=new interceptor();
        InterceptorRegistration loginRegistry=registry.addInterceptor(interceptor);
        loginRegistry.addPathPatterns("/**").excludePathPatterns("/index/login","/index/register","/index/logincheck","/index/signupcheck"
        ,"/static/**","/index","/home");
    }

}
