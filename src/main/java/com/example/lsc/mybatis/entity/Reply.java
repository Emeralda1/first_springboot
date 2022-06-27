package com.example.lsc.mybatis.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@Component("reply")
public class Reply {
    private String rid;
    private String tip;
    private String content;
    private String submitter;
    private Date rDate;
}
