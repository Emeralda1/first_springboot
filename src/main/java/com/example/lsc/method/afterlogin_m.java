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
    @Autowired
    private Users users;
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public void editshowname(user u) {
        UpdateWrapper<Users> usersUpdateWrapper=new UpdateWrapper<>();
        usersUpdateWrapper.eq("USERNAME",u.getUsername()).set("SHOWNAME",u.getShowname());
        usersMapper.update(null,usersUpdateWrapper);
    }
    @Override
    public Map<String, Object> fileUpload(MultipartFile file, String tempPath) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (null == file) {
            resultMap.put("result", false);
            resultMap.put("msg", "获取上传文件失败,请检查file上传组件的名称是否正确");
        } else if (file.isEmpty()) {
            resultMap.put("result", false);
            resultMap.put("msg", "没有选择文件");
        } else {
            File fileDir = new File(tempPath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            String filename = file.getOriginalFilename();
            filename = tempPath +"\\"+ filename;
            File dest = new File(filename);
            try {
                file.transferTo(dest);
                resultMap.put("result", "true");
                resultMap.put("msg", "上传成功");
                resultMap.put("filePath", filename);
            } catch (IOException e) {
                e.printStackTrace();
                resultMap.put("result", "false");
                resultMap.put("msg", "文件上传发生异常");
            }
        }
        return resultMap;
    }


    @Override
    public void editphoto(user u) {
        UpdateWrapper<Users> usersUpdateWrapper=new UpdateWrapper<>();
        usersUpdateWrapper.eq("USERNAME",u.getUsername()).set("PHOTOPATH",u.getPhotopath());
        System.out.println(u.getUsername()+u.getPhotopath());
        usersMapper.update(null,usersUpdateWrapper);
    }
}
