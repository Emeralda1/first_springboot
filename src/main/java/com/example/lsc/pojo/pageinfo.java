package com.example.lsc.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component("pages")
public class pageinfo {
        //分页类
        private Integer current;//当前页
        private Integer size;//每页显示条数
        private Long total;//返回记录总数
        private List<topic> topicList;//用于存放返回的记录
}
