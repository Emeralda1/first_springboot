package com.example.lsc.pojo;

import com.example.lsc.mybatis.entity.Users;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component("user")
@Data
public class user extends Users {
private String date;
}
