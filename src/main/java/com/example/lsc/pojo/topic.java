package com.example.lsc.pojo;
import com.example.lsc.mybatis.entity.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("topics")
@Data
public class topic extends Topic{
    private String date;
    private user u;
    private List<reply> replies;
}
