package com.example.lsc.method;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lsc.mybatis.entity.*;
import com.example.lsc.pojo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Topic_create {
    public  String getUUID();//生成随机ID
    public topic inittopic(Topic t);//初始化单个话题
    public IPage<Topic> getall_topic(Page<Topic> topicPage,String cate);//根据分页条件去获取记录
    public void newtopic(topic t);//发表新话题
    public List<reply> getReplies(topic t);//根据传入的话题获取此话题的所有回复
    public reply initreply(Reply r);//初始化单个回复
    public List<topic> topiclist(IPage<Topic> iPage);//遍历分页后的记录，将实体类话题转化为pojo类话题
    public topic gettopic(String tid);//获取单个话题
    public void newreply(reply r);//发表新回复
    public List<topic> mytopic(String username);//获取历史发表话题
    public List<reply> myreply(String username);//获取历史回复记录
    public void dtopic(String tid); //删除话题
    public void dreply(String rid);//删除回复
    public void exp(String uname,int e);//更改经验值
    public List<topic> search(String title);//搜索话题
}
