package com.example.lsc.method;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lsc.dao.entity.Users;
import com.example.lsc.dao.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lsc.pojo.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("beforelogin")
public class beforelogin_m implements beforelogin{
    @Autowired
    private Users users;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private msg m;
    @Autowired
    private user user;
    @Override
    public user getprofile(user u){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("USERNAME","EMAIL","SHOWNAME","SIGNDATE","PHOTOPATH").eq("USERNAME",u.getUsername());
        users=usersMapper.selectOne(queryWrapper);
        user.setUsername(users.getUsername());user.setEmail(users.getEmail());user.setShowname(users.getShowname());
        user.setSigndate(users.getSigndate());user.setPhotopath(users.getPhotopath());
        return user;
    }
    public msg logincheck(user u) {
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
        users=u;
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("USERNAME").eq("USERNAME",u.getUsername());
        long count=usersMapper.selectCount(queryWrapper);
        if (count>0){
            m.setInfo1("false");
        }
        else m.setInfo1("true");
        queryWrapper.select("EMAIL").eq("EMAIL",u.getEmail());
        if (count>0){
            m.setInfo2("false");
        }
        else m.setInfo2("true");
        if (m.getInfo1().equals("true")&&m.getInfo2().equals("true")){
            users.setPhotopath("/static/img/profile_photo/default.jpeg");
            usersMapper.insert(users);
        }
        return m;
    }
}
