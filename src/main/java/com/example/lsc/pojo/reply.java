package com.example.lsc.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
import com.example.lsc.mybatis.entity.*;
@Data
@Component("replies")
public class reply extends Reply{
    //实体回复类的子类
    private String title;//此回复属于话题的标题
    private String date;//格式化后的日期
    private user u;//发表此回复的用户
}
