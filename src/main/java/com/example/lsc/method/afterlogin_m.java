package com.example.lsc.method;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.lsc.mybatis.entity.Users;
import com.example.lsc.mybatis.mapper.UsersMapper;
import com.example.lsc.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("afterlogin")
public class afterlogin_m implements afterlogin{
    //自动连接User实体类的数据库操作接口类
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public void editshowname(user u) {
        //更新条件构造器
        UpdateWrapper<Users> usersUpdateWrapper=new UpdateWrapper<>();
        usersUpdateWrapper.eq("USERNAME",u.getUsername()).set("SHOWNAME",u.getShowname());
        usersMapper.update(null,usersUpdateWrapper);
    }
    @Override
    public Map<String, Object> fileUpload(MultipartFile file, String tempPath) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (null == file) {                           //如果传入文件值为空
            resultMap.put("result", false);
            resultMap.put("msg", "获取上传文件失败");
        } else if (file.isEmpty()) {                    //如果传入文件对象为空
            resultMap.put("result", false);
            resultMap.put("msg", "没有选择文件");
        } else {
            File fileDir = new File(tempPath); //设定传入的目录，没有则创建
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            String filename = file.getOriginalFilename();//获取传入文件名称
            filename = tempPath +"\\"+ filename;//设定传入文件名称并带上完整路径
            File dest = new File(filename);//设定最终存入文件路径
            try {
                file.transferTo(dest);//将文件传到最终路径中
                resultMap.put("result", "true");
                resultMap.put("msg", "上传成功");
                resultMap.put("filePath", filename);
            } catch (IOException e) {
                e.printStackTrace();
                resultMap.put("result", "false");
                resultMap.put("msg", "文件上传发生异常");
            }
        }
        System.out.println(resultMap.get("msg"));
        return resultMap;
    }


    @Override
    public void editphoto(user u) {
        //更新条件构造器，根据传入user对象的相关属性值更改头像文件的路径
        UpdateWrapper<Users> usersUpdateWrapper=new UpdateWrapper<>();
        usersUpdateWrapper.eq("USERNAME",u.getUsername()).set("PHOTOPATH",u.getPhotopath());
        System.out.println(u.getUsername()+u.getPhotopath());
        usersMapper.update(null,usersUpdateWrapper);
    }
}
