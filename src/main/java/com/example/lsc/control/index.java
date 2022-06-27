package com.example.lsc.control;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lsc.mybatis.entity.Topic;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.example.lsc.method.*;
import com.example.lsc.pojo.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class index {
    private String current_tid;
    @Autowired
    private beforelogin b;
    @Autowired
    private msg m;
    @Autowired
    private user user;
    @Autowired
    private afterlogin a;
    @Qualifier("topics")
    @Autowired
    private topic topic;
    @Autowired
    private reply reply;
    @Autowired
    private topic_create_m tc;
    @RequestMapping("/index/login")
    private String login(){
        return "login";
    }
    @RequestMapping("/index/register")
    private String register(){
        return "register";
    }
    @RequestMapping("/index/logincheck")
    public void logincheck(user s, HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        m=b.logincheck(s);
        if (m.getInfo1().equals("true")){
            user=b.getprofile(s);
            session.setAttribute("user",user);
            System.out.println(user.getUsername());
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
    public String index() throws UnsupportedEncodingException {
        return "redirect:/home?page=1&cate="+ URLEncoder.encode(("全部"),"UTF-8");
    }
    @RequestMapping("/home")
    public String home(Model model,int page,String cate,HttpSession session){
        Page<Topic> topicPage=new Page<>(page,4);
        IPage<Topic> topicIPage=tc.getall_topic(topicPage,cate);
        List<topic> fls=tc.topiclist(topicIPage);
        model.addAttribute("cate",cate);
        model.addAttribute("topiclist",fls);
        model.addAttribute("totalpages",topicIPage.getPages());
        model.addAttribute("currentpage",page);
        return "index";
    }
    @RequestMapping("/topic")
    public String showtopic(String tid,Model model){
        topic=tc.gettopic(tid);
        current_tid=tid;
        model.addAttribute("topic",topic);
        return "topic";
    }
    @RequestMapping("/publish")
    public String gopublish(){
        return "publish";
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
    @RequestMapping("/setnewtopic")
    public String publish(topic t,HttpSession session,Model model) throws UnsupportedEncodingException {
        user=(user)session.getAttribute("user");
        topic.setTitle(t.getTitle());
        topic.setContent(t.getContent());
        topic.setCate(t.getCate());
        topic.setSubmitter(user.getUsername());
        tc.newtopic(topic);
        model.addAttribute("cate",topic.getCate());
        return "redirect:/home?page=1&cate="+ URLEncoder.encode((topic.getCate()),"UTF-8");
    }
    @RequestMapping("/setnewreply")
    public String reply(reply r,HttpSession session,String tid,Model model) throws UnsupportedEncodingException {
        user=(user)session.getAttribute("user");
        reply.setTip(current_tid);
        reply.setContent(r.getContent());
        reply.setSubmitter(user.getUsername());
        tc.newreply(reply);
        model.addAttribute("cate",topic.getCate());
        return "redirect:/topic?tid="+current_tid;
    }
    @RequestMapping("/mytopic")
    public String mytopic(HttpSession session,Model model){
        user=(user)session.getAttribute("user");
        List<topic> ls=tc.mytopic(user.getUsername());
        model.addAttribute("topiclist",ls);
        return "mytopic";
    }
    @RequestMapping("/myreply")
    public String myreply(HttpSession session,Model model){
        user=(user)session.getAttribute("user");
        List<reply> ls=tc.myreply(user.getUsername());
        model.addAttribute("replylist",ls);
        return "myreply";
    }
    @RequestMapping("deletetopic")
    public String deletetopic(int tid){
        tc.dtopic(tid);
        return "redirect:/mytopic";
    }
    @RequestMapping("deletereply")
    public String deletereply(int rid){
        tc.dreply(rid);
        return "redirect:/myreply";
    }
}
