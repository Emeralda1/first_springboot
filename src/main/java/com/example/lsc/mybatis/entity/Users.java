package com.example.lsc.mybatis.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

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
