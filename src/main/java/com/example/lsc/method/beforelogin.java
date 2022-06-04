package com.example.lsc.method;
import org.springframework.stereotype.Repository;
import com.example.lsc.pojo.*;
@Repository
public interface beforelogin {
    public msg logincheck(user u);
    public msg signupcheck(user u);
    public user getprofile(user u);
}
