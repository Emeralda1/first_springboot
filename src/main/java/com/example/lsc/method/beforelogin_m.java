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
    @Autowired
    private Users users;
    @Autowired
    private user user;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private msg m;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public user getprofile(user u){
        user=new user();
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("USERNAME","EMAIL","SHOWNAME","SIGNDATE","PHOTOPATH","EXP").eq("USERNAME",u.getUsername());
        users=usersMapper.selectOne(queryWrapper);
        user.setUsername(users.getUsername());user.setEmail(users.getEmail());user.setShowname(users.getShowname());
        user.setDate(simpleDateFormat.format(users.getSigndate()));user.setPhotopath(users.getPhotopath());user.setExp((users.getExp()));
        return user;
    }
    public msg logincheck(user u) {
        user=new user();
        users=u;
        System.out.println(u.getPassword());
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
        user=new user();
        users=u;
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username").eq("username",u.getUsername());
        long count=usersMapper.selectCount(queryWrapper);
        if (count>0){
            m.setInfo1("false");
        }
        else m.setInfo1("true");
        queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username").eq("email",u.getEmail());
        count=usersMapper.selectCount(queryWrapper);
        System.out.println(count);
        if (count>0){
            m.setInfo2("false");
        }
        else m.setInfo2("true");
        System.out.println(u.getEmail());
        System.out.println(m.toString());
        if (m.getInfo1().equals("true")&&m.getInfo2().equals("true")){
            users.setPhotopath("\\static\\img\\profile_photo\\default.jpeg");
            users.setExp(5);
            usersMapper.insert(users);
        }
        return m;
    }
}
