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
    //当前正在操作的话题的tid，比如当前正在回复的话题
    private String current_tid;
    //自动连接登录前相关实现类
    @Autowired
    private beforelogin b;
    //自动连接提示信息类
    @Autowired
    private msg m;
    //自动连接user pojo类
    @Autowired
    private user user;
    @Autowired
    //自动连接登录后相关操作实现类
    private afterlogin a;
    //自动连接话题 pojo类
    @Qualifier("topics")
    @Autowired
    private topic topic;
    //自动连接回复 pojo类
    @Autowired
    private reply reply;
    //自动连接话题相关方法实现类
    @Autowired
    private topic_create_m tc;
    //指定登录页面映射地址
    @RequestMapping("/index/login")
    private String login(){
        return "login";
    }
    //指定注册页面映射地址
    @RequestMapping("/index/register")
    private String register(){
        return "register";
    }
    //指定登录验证接口映射地址
    @RequestMapping("/index/logincheck")
    public void logincheck(user s, HttpSession session, HttpServletResponse response) throws IOException {
        //由ajax传入json 调用登录验证方法 将json返回给前端提示对应的错误或者登录成功信息
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        m=b.logincheck(s);
        if (m.getInfo1().equals("true")){
            user=b.getprofile(s);
            session.setAttribute("user",user);
        }
        Gson gson=new Gson();
        String json=gson.toJson(m);//将类的属性键值对映射为json字符串
        out.print(json);
        out.flush();
        out.close();
    }
    //指定注册验证接口映射地址
    @RequestMapping("/index/signupcheck")
    public void signupcheck(user s,HttpSession session,HttpServletResponse response) throws IOException {
        //实现方法同登录验证类似
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        m=b.signupcheck(s);
        if (m.getInfo1().equals("true")&&m.getInfo2().equals("true")){
            session.setAttribute("user",s);//如果消息通知类都为true才存入session
        }
        Gson gson=new Gson();
        String json=gson.toJson(m);
        out.print(json);
        out.flush();
        out.close();
    }
    //指定登录成功后进入的主页映射地址
    @RequestMapping("/index")
    public String index() throws UnsupportedEncodingException {
        //直接带参数重定向到home页面
        return "redirect:/home?page=1&cate="+ URLEncoder.encode(("全部"),"UTF-8");
    }
    //指定展示话题的主页映射地址
    @RequestMapping("/home")
    public String home(Model model,int page,String cate,HttpSession session){
        //mybatis-plus自带分页组件
        Page<Topic> topicPage=new Page<>(page,4);//设定单页最大显示记录数
        IPage<Topic> topicIPage=tc.getall_topic(topicPage,cate);//方法返回查到的记录列表
        List<topic> fls=tc.topiclist(topicIPage);//由于记录列表泛型为存数据库字段的实体类Topic  现将其转化为经过数据处理后的pojo类topic
        model.addAttribute("cate",cate);//当前类别
        model.addAttribute("topiclist",fls);//话题列表
        model.addAttribute("totalpages",topicIPage.getPages());//总页数
        model.addAttribute("currentpage",page);//当前页
        return "index";
    }
    //指定进入单个话题页面的映射地址
    @RequestMapping("/topic")
    public String showtopic(String tid,Model model){
        topic=tc.gettopic(tid);//从前端获取当前话题tid
        current_tid=tid;//将此话题tid赋值为当前话题current_id方便后面的回复操作
        model.addAttribute("topic",topic);
        return "topic";
    }
    //指定发表话题接口的映射地址
    @RequestMapping("/publish")
    public String gopublish(){
        return "publish";
    }
    //指定展示个人资料页面的映射地址
    @RequestMapping("/index/profile")
    public String profile(HttpSession session){
        user=b.getprofile(user);
        session.setAttribute("user",user);
        return "profile";
    }
    //指定编辑昵称接口的映射地址
    @RequestMapping("/index/editshowname")
    public void editprofile(String showname,HttpSession session,HttpServletResponse response) throws IOException {
        user=(user)session.getAttribute("user");
        user.setShowname(showname);//给user对象设定输入的昵称再传入接口去改变昵称
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
    //指定更改头像接口的映射地址
    @RequestMapping("/uploadphoto")
    public void uploadphoto(HttpSession session, @RequestParam("file") MultipartFile[] file, HttpServletResponse response) throws IOException {
        //指定放入头像图片的目录
        String path="A:\\javaee\\lsc_springboot\\target\\classes\\static\\img\\profile_photo";//设定文件上传目录
        Map<String, Object> resultMap = a.fileUpload(file[0],path);//获取执行结果map键值对
        String filename="\\static\\img\\profile_photo\\"+file[0].getOriginalFilename();//获取文件名称存入数据库
        if (resultMap.get("result").equals("true")){
            user=(user)session.getAttribute("user");
            user.setPhotopath(filename);
            a.editphoto(user);
            session.setAttribute("user",user);
        }
        response.sendRedirect("/index/profile");
    }
    //指定登出接口的映射地址
    @RequestMapping("/logout")
    public void logout(HttpSession session,HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/index/login");
    }
    //指定发表新话题的映射地址
    @RequestMapping("/setnewtopic")
    public String publish(topic t,HttpSession session,Model model) throws UnsupportedEncodingException {
        user=(user)session.getAttribute("user");
        topic.setTitle(t.getTitle());
        topic.setContent(t.getContent());
        topic.setCate(t.getCate());
        topic.setSubmitter(user.getUsername());//设定话题各属性值再存入数据库
        tc.newtopic(topic);
        model.addAttribute("cate",topic.getCate());
        return "redirect:/home?page=1&cate="+ URLEncoder.encode((topic.getCate()),"UTF-8");
    }
    //指定回复接口的映射地址
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
    //指定展示我发表话题页面的映射地址
    @RequestMapping("/mytopic")
    public String mytopic(HttpSession session,Model model){
        user=(user)session.getAttribute("user");
        List<topic> ls=tc.mytopic(user.getUsername());
        model.addAttribute("topiclist",ls);
        return "mytopic";
    }
    //指定我的回复的页面的映射地址
    @RequestMapping("/myreply")
    public String myreply(HttpSession session,Model model){
        user=(user)session.getAttribute("user");
        List<reply> ls=tc.myreply(user.getUsername());
        model.addAttribute("replylist",ls);
        System.out.println(ls);
        return "myreply";
    }
    //指定搜索接口的映射地址
    @RequestMapping("/search")
    public String search(String title,Model model){
        List<topic> ls=tc.search(title);
        model.addAttribute("topiclist",ls);
        return "search";
    }
    //指定删除指定话题的接口的映射地址
    @RequestMapping("deletetopic")
    public String deletetopic(String tid){
        tc.dtopic(tid);
        return "redirect:/mytopic";
    }
    //指定删除指定回复的接口的映射地址
    @RequestMapping("deletereply")
    public String deletereply(String rid){
        tc.dreply(rid);
        return "redirect:/myreply";
    }
}
