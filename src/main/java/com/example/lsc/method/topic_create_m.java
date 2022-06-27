package com.example.lsc.method;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lsc.mybatis.mapper.ReplyMapper;
import com.example.lsc.mybatis.mapper.TopicMapper;
import com.example.lsc.mybatis.mapper.UsersMapper;
import com.example.lsc.mybatis.mapper.getall;
import com.example.lsc.pojo.*;
import com.example.lsc.mybatis.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Service("topic_create")
public class topic_create_m implements Topic_create {
    @Autowired
    private Users users;
    @Qualifier("topic")
    @Autowired
    private Topic e_topic;
    @Qualifier("reply")
    @Autowired
    private Reply e_reply;
    @Autowired
    private user user;
    @Autowired
    private topic p_topic;
    @Autowired
    private reply p_reply;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private getall getall;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
    public topic inittopic(Topic e_t) {
        user=new user();
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username","showname","photopath","exp").eq("username",e_t.getSubmitter());
        users=usersMapper.selectOne(queryWrapper);
        user.setUsername(users.getUsername());user.setShowname(users.getShowname());user.setExp((users.getExp()));
        user.setPhotopath(users.getPhotopath());
        p_topic.setTid(e_t.getTid());p_topic.setTitle(e_t.getTitle());p_topic.setContent(e_t.getContent());
        p_topic.setDate(simpleDateFormat.format(e_t.getTDate()));p_topic.setU(user);p_topic.setCate(e_t.getCate());
        return p_topic;
    }

    @Override
    public IPage<Topic> getall_topic(Page<Topic> topicPage,String cate) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        if (cate.equals("全部")) {
            queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").orderByDesc("t_date");
        }
        else {
            queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").orderByDesc("t_date").eq("cate",cate);
        }
        IPage<Topic> topicIPage = topicMapper.selectPage(topicPage, queryWrapper);
        for (Topic t:topicIPage.getRecords()){
            System.out.println(t.toString());
        }
        return topicIPage;
    }

    @Override
    public void newtopic(topic t) {
        e_topic.setTid(getUUID());
        e_topic.setTitle(t.getTitle());e_topic.setSubmitter(t.getSubmitter());e_topic.setContent(t.getContent());
        e_topic.setCate(t.getCate());
        topicMapper.insert(e_topic);
    }

    @Override
    public List<reply> getReplies(topic t) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tip","content","r_date","submitter","rid").eq("tip",t.getTid()).orderByAsc("r_date");
        List<Reply> ls=replyMapper.selectList(queryWrapper);
        List<reply> fls=new ArrayList<>();
        if (ls.size()>0) {
            for (Reply r : ls) {
                reply re=new reply();
                System.out.println(r.toString());
                p_reply=initreply(r);
                re.setTip(p_reply.getTip());re.setContent(p_reply.getContent());re.setU(p_reply.getU());
                re.setDate(p_reply.getDate());re.setSubmitter(p_reply.getSubmitter());
                System.out.println(re.toString());
                fls.add(re);
            }
        }
        System.out.println(fls);
        return fls;
    }

    @Override
    public reply initreply(Reply r) {
        user=new user();
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username","showname","photopath","exp").eq("username",r.getSubmitter());
        users=usersMapper.selectOne(queryWrapper);
        user.setUsername(users.getUsername());user.setShowname(users.getShowname());user.setExp((users.getExp()));
        user.setPhotopath(users.getPhotopath());
        p_reply=new reply();
        p_reply.setTip(r.getTip());p_reply.setContent(r.getContent());p_reply.setU(user);
        p_reply.setDate(simpleDateFormat.format(r.getRDate()));p_reply.setRid(r.getRid());
        return p_reply;
    }

    @Override
    public List<topic> topiclist(IPage<Topic> iPage) {
        List<topic> fls=new ArrayList();
        for(int i=0;i<iPage.getRecords().size();i++){
            p_topic=inittopic(iPage.getRecords().get(i));
            topic t=new topic();
            t.setTid(p_topic.getTid());t.setContent(p_topic.getContent());t.setDate(p_topic.getDate());
            t.setReplies(p_topic.getReplies());t.setCate(p_topic.getCate());t.setU(p_topic.getU());
            t.setTitle(p_topic.getTitle());
            fls.add(t);
        }
        return fls;
    }

    @Override
    public topic gettopic(String tid) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").eq("tid",tid);
        e_topic=new Topic();
        e_topic=topicMapper.selectOne(queryWrapper);
        p_topic=new topic();
        p_topic=inittopic(e_topic);
        p_topic.setReplies(getReplies(p_topic));
        return p_topic;
    }

    @Override
    public void newreply(reply r) {
        e_reply.setRid(getUUID());
        e_reply.setTip(r.getTip());e_reply.setContent(r.getContent());
        e_reply.setSubmitter(r.getSubmitter());
        replyMapper.insert(e_reply);
    }

    @Override
    public List<topic> mytopic(String username) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").eq("submitter",username);
        List<Topic> ls=topicMapper.selectList(queryWrapper);
        List<topic> fls=new ArrayList<>();
        if (ls.size()>0){
            for (Topic t:ls){
                topic to=new topic();
                p_topic=inittopic(t);
                to.setTid(p_topic.getTid());to.setContent(p_topic.getContent());to.setCate(p_topic.getCate());
                to.setU(p_topic.getU());to.setTitle(p_topic.getTitle());to.setDate(p_topic.getDate());
                fls.add(to);
            }
        }
        return fls;
    }

    @Override
    public List<reply> myreply(String username) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tip","content","r_date","submitter","rid").eq("submitter",username);
        List<Reply> ls=replyMapper.selectList(queryWrapper);
        List<reply> fls=new ArrayList<>();
        if(ls.size()>0){
            for (Reply r:ls){
                reply re=new reply();
                p_reply=initreply(r);
                re.setTip(p_reply.getTip());re.setContent(p_reply.getContent());re.setDate(p_reply.getDate());
                re.setU(p_reply.getU());re.setSubmitter(p_reply.getSubmitter());re.setTitle(gettopic(p_reply.getTip()).getTitle());
                re.setRid(p_reply.getRid());
                fls.add(re);
            }
        }
        return fls;
    }

    @Override
    public void dtopic(String tid) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid",tid);
        topicMapper.delete(queryWrapper);
    }

    @Override
    public void dreply(String rid) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",rid);
        replyMapper.delete(queryWrapper);
    }

    @Override
    public void exp(String uname, int e) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("exp").eq("username",uname);
        int exp=usersMapper.selectOne(queryWrapper).getExp();
        UpdateWrapper<Users> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("username",uname).set("exp",exp+e);
        usersMapper.update(null,updateWrapper);
    }

    @Override
    public List<topic> search(String title) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").like("title",title);
        List<Topic> ls=topicMapper.selectList(queryWrapper);
        List<topic> fls=new ArrayList<>();
        if (ls.size()>0){
            for (Topic t:ls){
                topic to=new topic();
                p_topic=inittopic(t);
                to.setTid(p_topic.getTid());to.setContent(p_topic.getContent());to.setCate(p_topic.getCate());
                to.setU(p_topic.getU());to.setTitle(p_topic.getTitle());to.setDate(p_topic.getDate());
                fls.add(to);
            }
        }
        return fls;
    }
}
