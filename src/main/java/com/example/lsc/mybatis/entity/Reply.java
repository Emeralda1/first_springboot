package com.example.lsc.mybatis.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

//和数据库字段一一对应，用于操作数据库
@Data//lombok注解，可自动生成get()、set()、toString()等方法
@Component("reply")
public class Reply {
    private String tip;
    private String content;
    private String submitter;
    private Date rDate;
    private String rid;
}
