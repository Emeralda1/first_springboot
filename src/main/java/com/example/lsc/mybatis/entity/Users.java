package com.example.lsc.mybatis.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
//和数据库字段一一对应，用于操作数据库
@Data
@Component("users")
public class Users {
    private String username;
    private String password;
    private String email;
    private String showname;
    private Date signdate;
    private String photopath;
    private int exp;
}
