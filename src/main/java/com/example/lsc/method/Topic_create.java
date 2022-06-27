package com.example.lsc.method;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lsc.mybatis.entity.*;
import com.example.lsc.pojo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Topic_create {
    public  String getUUID();
    public topic inittopic(Topic t);
    public IPage<Topic> getall_topic(Page<Topic> topicPage,String cate);
    public void newtopic(topic t);
    public List<reply> getReplies(topic t);
    public reply initreply(Reply r);
    public List<topic> topiclist(IPage<Topic> iPage);
    public topic gettopic(String tid);
    public void newreply(reply r);
    public List<topic> mytopic(String username);
    public List<reply> myreply(String username);
    public void dtopic(String tid);
    public void dreply(String rid);
    public void exp(String uname,int e);
    public List<topic> search(String title);
}
