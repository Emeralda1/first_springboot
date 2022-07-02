package com.example.lsc.method;
import org.springframework.stereotype.Repository;
import com.example.lsc.pojo.*;
@Repository
public interface beforelogin {
    public msg logincheck(user u);//登录验证
    public msg signupcheck(user u);//注册验证
    public user getprofile(user u);//获取个人资料
}
