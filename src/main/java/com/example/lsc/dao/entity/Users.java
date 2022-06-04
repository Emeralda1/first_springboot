package com.example.lsc.dao.entity;

import com.example.lsc.pojo.user;
import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component("users")
public class Users {
    private String username;
    private String password;
    private String email;
    private String showname;
    private String signdate;
    private String photopath;
}
