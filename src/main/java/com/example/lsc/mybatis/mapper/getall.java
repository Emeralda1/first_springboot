package com.example.lsc.mybatis.mapper;

import com.example.lsc.mybatis.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface getall {
    List<Topic> select_mytopic(Topic t);
}
