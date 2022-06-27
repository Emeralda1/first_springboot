package com.example.lsc.method;

import com.example.lsc.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("interceptor")
public class interceptor implements HandlerInterceptor {
    @Autowired
    user u;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        u=(user)session.getAttribute("user");
        System.out.println(u);
        if (u!=null){
            return true;
        }
        else {
            request.getRequestDispatcher("/index/login").forward(request,response);
            return false;
        }
    }
}
