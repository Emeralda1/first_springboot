package com.example.lsc.method;

import com.example.lsc.pojo.user;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Repository
public interface afterlogin {
    public void editshowname(user u);//编辑昵称
    public Map<String, Object> fileUpload(MultipartFile file, String tempPath);//头像文件上传
    public void editphoto(user u);//更改头像文件的路径
}
