package com.example.lsc.method;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lsc.mybatis.mapper.ReplyMapper;
import com.example.lsc.mybatis.mapper.TopicMapper;
import com.example.lsc.mybatis.mapper.UsersMapper;
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
    //自动连接实体类用户
    @Autowired
    private Users users;
    //自动连接实体类话题
    @Qualifier("topic")
    @Autowired
    private Topic e_topic;
    //自动连接实体类回复
    @Qualifier("reply")
    @Autowired
    private Reply e_reply;
    //自动连接pojo类用户
    @Autowired
    private user user;
    //自动连接pojo类话题
    @Autowired
    private topic p_topic;
    @Autowired
    //自动连接pojo类回复
    private reply p_reply;
    //自动连接话题实体类数据库操作接口
    @Autowired
    private TopicMapper topicMapper;
    //自动连接回复实体类数据库操作接口
    @Autowired
    private ReplyMapper replyMapper;
    //自动连接用户实体类数据库操作接口
    @Autowired
    private UsersMapper usersMapper;
    //格式化日期
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public String getUUID() {
        //java自带生成不重复随机ID的方法，通过获取当前系统日期、时间、设备编号进行哈希运算生成的32位ID
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
    public topic inittopic(Topic e_t) {
        //首先获取发表此话题的用户对象
        user=new user();
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username","showname","photopath","exp").eq("username",e_t.getSubmitter());
        users=usersMapper.selectOne(queryWrapper);
        user.setUsername(users.getUsername());user.setShowname(users.getShowname());user.setExp((users.getExp()));
        user.setPhotopath(users.getPhotopath());
        //格式化话题的日期、设置话题的发表用户
        p_topic.setTid(e_t.getTid());p_topic.setTitle(e_t.getTitle());p_topic.setContent(e_t.getContent());
        p_topic.setDate(simpleDateFormat.format(e_t.getTDate()));p_topic.setU(user);p_topic.setCate(e_t.getCate());
        return p_topic;
    }

    @Override
    public IPage<Topic> getall_topic(Page<Topic> topicPage,String cate) {
        //根据控制层传入的Page对象去按照规则获取记录，例如设置了每页四条记录，这里就查询四条
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        if (cate.equals("全部")) {
            queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").orderByDesc("t_date");
        }
        else {                  //根据类别来查询
            queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").orderByDesc("t_date").eq("cate",cate);
        }
        IPage<Topic> topicIPage = topicMapper.selectPage(topicPage, queryWrapper);//mybatis-plus分页插件自带接口
        return topicIPage;
    }

    @Override
    public void newtopic(topic t) {
        //发表新话题，id为随机生成再根据传入的话题对象插入到数据库
        e_topic.setTid(getUUID());
        e_topic.setTitle(t.getTitle());e_topic.setSubmitter(t.getSubmitter());e_topic.setContent(t.getContent());
        e_topic.setCate(t.getCate());
        topicMapper.insert(e_topic);
    }

    @Override
    public List<reply> getReplies(topic t) {
        //根据传入话题对象的tid去查询属于此话题的所有回复
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tip","content","r_date","submitter","rid").eq("tip",t.getTid()).orderByAsc("r_date");
        List<Reply> ls=replyMapper.selectList(queryWrapper);
        List<reply> fls=new ArrayList<>();
        if (ls.size()>0) {
            //遍历实体类回复，将其转化为pojo类回复
            for (Reply r : ls) {
                //每次都生成新对象，不然传入到列表中的对象都会被最后一个所覆盖，导致列表中对象属性值全部一样
                reply re=new reply();
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
        //初始化单条回复，首先获取发表此回复的用户
        user=new user();
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username","showname","photopath","exp").eq("username",r.getSubmitter());
        users=usersMapper.selectOne(queryWrapper);
        user.setUsername(users.getUsername());user.setShowname(users.getShowname());user.setExp((users.getExp()));
        user.setPhotopath(users.getPhotopath());
        p_reply=new reply();
        //格式化日期
        p_reply.setTip(r.getTip());p_reply.setContent(r.getContent());p_reply.setU(user);
        p_reply.setDate(simpleDateFormat.format(r.getRDate()));p_reply.setRid(r.getRid());
        return p_reply;
    }

    @Override
    public List<topic> topiclist(IPage<Topic> iPage) {
        //将分页查询的结果转化为经过传到前端显示的列表
        List<topic> fls=new ArrayList();
        //遍历分页对象中所有记录，将其初始化后再装填此话题的所有回复
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
        //获取单个话题，根据传入tid返回pojo类话题对象去前端显示
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
        //回复话题，根据传入的pojo类回复对象转化为实体类再插入到数据库中
        e_reply.setRid(getUUID());
        e_reply.setTip(r.getTip());e_reply.setContent(r.getContent());
        e_reply.setSubmitter(r.getSubmitter());
        replyMapper.insert(e_reply);
    }

    @Override
    public List<topic> mytopic(String username) {
        //获取自己历史发表的话题，根据传入的用户名去获取属于此用户的话题
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").eq("submitter",username);
        List<Topic> ls=topicMapper.selectList(queryWrapper);
        List<topic> fls=new ArrayList<>();
        if (ls.size()>0){
            //遍历初始的数据库记录列表，将其转化为pojo类再返回到前端
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
        //获取历史回复的记录，根据传入用户名去获取此用户发表过的所有回复
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tip","content","r_date","submitter","rid").eq("submitter",username);
        List<Reply> ls=replyMapper.selectList(queryWrapper);
        List<reply> fls=new ArrayList<>();
        if(ls.size()>0){
            //遍历初始的数据库记录列表，将其转化为pojo类再返回到前端
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
        //根据传入tid去删除话题
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid",tid);
        topicMapper.delete(queryWrapper);
    }

    @Override
    public void dreply(String rid) {
        //根据传入rid去删除回复
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid",rid);
        replyMapper.delete(queryWrapper);
    }

    @Override
    public void exp(String uname, int e) {
        //根据传入的用户名和需增加的经验值e去修改用户经验值
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("exp").eq("username",uname);
        int exp=usersMapper.selectOne(queryWrapper).getExp();
        UpdateWrapper<Users> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("username",uname).set("exp",exp+e);
        usersMapper.update(null,updateWrapper);
    }

    @Override
    public List<topic> search(String title) {
        //根据传入的搜索内容去进行模糊查询
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("tid", "title", "content", "t_date", "submitter", "cate").like("title",title);
        List<Topic> ls=topicMapper.selectList(queryWrapper);
        List<topic> fls=new ArrayList<>();
        if (ls.size()>0){
            //遍历初始的数据库记录列表，将其转化为pojo类再返回到前端
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
