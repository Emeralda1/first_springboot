package com.example.lsc.control;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import com.example.lsc.method.*;
import com.example.lsc.pojo.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
public class index {
    @Autowired
    private beforelogin b;
    @Autowired
    private msg m;
    @Autowired
    private user user;
    @Autowired
    private afterlogin a;
    @RequestMapping("/index/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/index/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/index/logincheck")
    public void logincheck(user s, HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        session.setMaxInactiveInterval(3600);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        m=b.logincheck(s);
        if (m.getInfo1().equals("true")){
            user=b.getprofile(s);
            session.setAttribute("user",user);
        }
        Gson gson=new Gson();
        String json=gson.toJson(m);
        out.print(json);
        out.flush();
        out.close();
    }
    @RequestMapping("/index/signupcheck")
    public void signupcheck(user s,HttpSession session,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        m=b.signupcheck(s);
        if (m.getInfo1().equals("true")&&m.getInfo2().equals("true")){
            session.setAttribute("user",s);
        }
        Gson gson=new Gson();
        String json=gson.toJson(m);
        out.print(json);
        out.flush();
        out.close();
    }
    @RequestMapping("/index")
    public String loginsuccess(){
        return "index";
    }
    @RequestMapping("/index/profile")
    public String profile(){
        return "profile";
    }
    @RequestMapping("/index/editshowname")
    public void editprofile(String showname,HttpSession session,HttpServletResponse response) throws IOException {
        user=(user)session.getAttribute("user");
        user.setShowname(showname);
        a.editshowname(user);
        session.setAttribute("user",user);
        PrintWriter out=response.getWriter();
        m.setInfo1("true");
        Gson gson=new Gson();
        String json=gson.toJson(m);
        out.print(json);
        out.flush();
        out.close();
    }
    @RequestMapping("/uploadphoto")
    public void uploadphoto(HttpSession session, @RequestParam("file") MultipartFile[] file, HttpServletResponse response) throws IOException {
        String path="A:\\javaee\\lsc_springboot\\target\\classes\\static\\img\\profile_photo";
        Map<String, Object> resultMap = a.fileUpload(file[0],path);
        String filename="\\static\\img\\profile_photo\\"+file[0].getOriginalFilename();
        if (resultMap.get("result").equals("true")){
            user=(user)session.getAttribute("user");
            user.setPhotopath(filename);
            a.editphoto(user);
            session.setAttribute("user",user);
        }
        response.sendRedirect("/index/profile");
    }
    @RequestMapping("/logout")
    public void logout(HttpSession session,HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/index/login");
    }
    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
