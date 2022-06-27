package com.example.lsc.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
import com.example.lsc.mybatis.entity.*;
@Data
@Component("replies")
public class reply extends Reply{
    private String title;
    private String date;
    private user u;
}
