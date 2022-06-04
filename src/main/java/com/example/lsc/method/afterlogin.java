package com.example.lsc.method;

import com.example.lsc.pojo.user;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Repository
public interface afterlogin {
    public void editshowname(user u);
    public Map<String, Object> fileUpload(MultipartFile file, String tempPath);
    public void editphoto(user u);
}
