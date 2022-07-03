package com.example.lsc.method;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lsc.mybatis.entity.Users;
import com.example.lsc.mybatis.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lsc.pojo.*;

import java.text.SimpleDateFormat;

@Service("beforelogin")
public class beforelogin_m implements beforelogin{
    //自动连接用户实体类存储数据库字段
    @Autowired
    private Users users;
    //自动连接用户pojo类返回给前端
    @Autowired
    private user user;
    @Autowired
    //自动连接user实体的数据库操作接口类
    private UsersMapper usersMapper;
    @Autowired
    //自动连接消息提示类
    private msg m;
    //格式化日期，从数据库从获取的日期为英文，转化为如下格式
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public user getprofile(user u){
        //根据传入的用户pojo类的用户名属性去查询此用户其他信息
        user=new user();
        //条件构造器
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("USERNAME","EMAIL","SHOWNAME","SIGNDATE","PHOTOPATH","EXP").eq("USERNAME",u.getUsername());
        users=usersMapper.selectOne(queryWrapper);
        //将实体类user转化为pojo类user
        user.setUsername(users.getUsername());user.setEmail(users.getEmail());user.setShowname(users.getShowname());
        user.setDate(simpleDateFormat.format(users.getSigndate()));
        user.setPhotopath(users.getPhotopath());user.setExp((users.getExp()));
        return user;
    }
    public msg logincheck(user u) {
        //通过传入的user对象的账号密码属性去数据库比对
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("USERNAME").eq("USERNAME",u.getUsername()).eq("PASSWORD",u.getPassword());
        long count=usersMapper.selectCount(queryWrapper);
        if (count>0){
            m.setInfo1("true");
        }
        else m.setInfo1("false");
        return m;
    }
    @Override
    public msg signupcheck(user u) {
        //先通过传入user对象的账号和邮箱去与数据库比对看是否重复
        user=new user();
        users=u;
        //看用户名是否重复
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username").eq("username",u.getUsername());
        long count=usersMapper.selectCount(queryWrapper);
        if (count>0){
            m.setInfo1("false");
        }
        else m.setInfo1("true");
        //看邮箱是否重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username").eq("email",u.getEmail());
        count=usersMapper.selectCount(queryWrapper);
        if (count>0){
            m.setInfo2("false");
        }
        else m.setInfo2("true");
        //先设置初始头像，和初始经验值再将用户插入到数据库
        if (m.getInfo1().equals("true")&&m.getInfo2().equals("true")){
            users.setPhotopath("\\static\\img\\profile_photo\\default.jpeg");
            users.setExp(5);
            usersMapper.insert(users);
        }
        return m;
    }
}
