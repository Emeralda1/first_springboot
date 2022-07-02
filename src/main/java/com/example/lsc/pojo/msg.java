package com.example.lsc.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component("msg")
@Data
public class msg {
    //消息通知类
    private String info1;
    private String info2;
}
