package com.example.lsc.aspect;

import com.example.lsc.method.topic_create_m;
import com.example.lsc.pojo.reply;
import com.example.lsc.pojo.topic;
import com.example.lsc.pojo.user;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Aspect
@Component("exp")
public class exp {
    @Autowired
    private topic_create_m tc;
    @Pointcut("execution(* com.example.lsc.control.index.publish(..))")
    void pointcut1(){

    }
    @Pointcut("execution(* com.example.lsc.control.index.reply(..))")
    void pointcut2(){

    }
    @Around("pointcut1()")
    public String addexp1(ProceedingJoinPoint point) throws Throwable {
        System.out.println("exp");
        point.proceed();
        Object[] args=point.getArgs();
        HttpSession session=(HttpSession)args[1];
        topic t=(topic)args[0];
        user u=(user)session.getAttribute("user");
        tc.exp(u.getUsername(),30);
        return "redirect:/home?page=1&cate="+ URLEncoder.encode((t.getCate()),"UTF-8");
    }
    @Around("pointcut2()")
    public String addexp2(ProceedingJoinPoint point) throws Throwable {
        Object[] args=point.getArgs();
        HttpSession session=(HttpSession)args[1];
        String tid=(String)args[2];
        user u=(user)session.getAttribute("user");
        tc.exp(u.getUsername(),10);
        point.proceed();
        return "redirect:/topic?tid="+tid;
    }
}
