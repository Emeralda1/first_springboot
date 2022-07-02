package com.example.lsc.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lsc.mybatis.entity.Users;
import org.springframework.stereotype.Service;
//mybatis-plus提供的crud接口
@Service
public interface UsersMapper extends BaseMapper<Users> {

}
