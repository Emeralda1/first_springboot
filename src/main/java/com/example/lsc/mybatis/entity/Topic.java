package com.example.lsc.mybatis.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@Component("topic")
public class Topic {
    private String tid;
    private String title;
    private String content;
    private Date tDate;
    private String submitter;
    private String cate;
}
