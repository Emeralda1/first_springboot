package com.example.lsc.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component("pages")
public class pageinfo {
        private Integer current;
        private Integer size;
        private Long total;
        private List<topic> topicList;
}
