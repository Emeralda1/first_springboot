package com.example.lsc.pojo;
import com.example.lsc.mybatis.entity.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("topics")
@Data
public class topic extends Topic{
    //实体话题类的子类
    private String date;//格式化后的日期
    private user u;//话题发表者用户
    private List<reply> replies;//此话题的所有回复
}
