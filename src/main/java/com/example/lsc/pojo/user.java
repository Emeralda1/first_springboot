package com.example.lsc.pojo;

import com.example.lsc.mybatis.entity.Users;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component("user")
@Data
public class user extends Users {
    //实体用户类的子类
private String date;//格式化后的日期
}
