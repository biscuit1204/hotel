package com.example.tavern.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tavern.pojo.User;
import com.example.tavern.pojo.UserList;
import com.example.tavern.pojo.vo.UserVo;
import com.example.tavern.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcb
 * @since 2021-08-29
 */
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    User user;
    @Autowired
    UserList userList;
    @Autowired
    UserService userService;

    @RequestMapping("/table.html")
    public String goUserTable(){
        return "people/user/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public UserList getUserList(@RequestParam(value = "page") Integer pn,
                                @RequestParam(value = "limit") Integer limit){
        Page<User> pages = new Page<>(pn, limit);
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Wrapper.select("id", "name","phone", "create_time", "vip","card","img","open_key","sex");
        Page<User> page = userService.page(pages, Wrapper);
        List<User> records = page.getRecords();
        for (User record : records) {
            String sex = record.getSex();
            if (sex.equals("1")){
                record.setSex("男");
            }else if(sex.equals("2")){
                record.setSex("女");
            }else{
                record.setSex("未知");
            }
        }
        userList.setData(records);
        userList.setData(page.getRecords());
        userList.setCount(page.getTotal());
        userList.setCode(0);
        return userList;
    }

    @ResponseBody
    @RequestMapping("/getSearchList")
    public UserList getUserSearchList(@RequestParam(value = "page") Integer pn,
                                      @RequestParam(value = "limit") Integer limit,
                                      @RequestParam(value = "result") String result){
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<User> pages = new Page<>(pn, limit);
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        System.out.println(jsonObject);
        if (jsonObject.getString("id")!=null) {
            Wrapper.like("id", jsonObject.getString("id"));
        }
        if (jsonObject.getString("name") != null) {
            Wrapper.like("name", jsonObject.getString("name"));
        }
        if (jsonObject.getString("phone") != null) {
            Wrapper.like("phone", jsonObject.getString("phone"));
        }

        Wrapper.eq("is_out", 1);
        Wrapper.select("id", "name","phone", "create_time", "vip","card","img","open_key","sex");
        Page<User> page = userService.page(pages, Wrapper);
        System.out.println(page.getRecords());
        List<User> records = page.getRecords();
        for (User record : records) {
            String sex = record.getSex();
            if (sex.equals("1")){
                record.setSex("男");
            }else if(sex.equals("2")){
                record.setSex("女");
            }else{
                record.setSex("未知");
            }
        }
        userList.setData(records);
        userList.setData(page.getRecords());
        userList.setCount(page.getTotal());
        userList.setCode(0);
        return userList;
    }

    @ResponseBody
    @RequestMapping("/Del")
    public UserList DelUser(@RequestParam(value = "page") Integer pn,
                            @RequestParam(value = "limit") Integer limit,
                            @RequestParam(value = "id") String id){

        user.setId(Integer.valueOf(id));
        user.setIsOut("0");
        userService.updateById(user);
        Page<User> pages = new Page<>(pn, limit);
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Wrapper.select("id", "name","phone", "create_time", "vip","card","img","open_key","sex");
        Page<User> page = userService.page(pages, Wrapper);
        List<User> records = page.getRecords();
        for (User record : records) {
            String sex = record.getSex();
            if (sex.equals("1")){
                record.setSex("男");
            }else if(sex.equals("2")){
                record.setSex("女");
            }else{
                record.setSex("未知");
            }
        }
        userList.setData(records);
        userList.setData(page.getRecords());
        userList.setCount(page.getTotal());
        userList.setCode(0);
        return userList;
    }

    @ResponseBody
    @RequestMapping("/DelList")
    public UserList DelUserList(@RequestParam(value = "page") Integer pn,
                                @RequestParam(value = "limit") Integer limit,
                                @RequestParam(value = "data") String data
    ) {
        JSONArray tableData = JSONArray.parseArray(data);
        for (Iterator iterator = tableData.iterator(); iterator.hasNext(); ) {
            JSONObject job = (JSONObject) iterator.next();
            user.setId(Integer.valueOf(job.get("id").toString()));
            user.setIsOut("0");
            userService.updateById(user);
        }
        Page<User> pages = new Page<>(pn, limit);
        QueryWrapper<User> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Wrapper.select("id", "name","phone", "create_time", "vip","card","img","open_key","sex");
        Page<User> page = userService.page(pages, Wrapper);
        List<User> records = page.getRecords();
        for (User record : records) {
            String sex = record.getSex();
            if (sex.equals("1")){
                record.setSex("男");
            }else if(sex.equals("2")){
                record.setSex("女");
            }else{
                record.setSex("未知");
            }
        }
        userList.setData(records);
        userList.setData(page.getRecords());
        userList.setCount(page.getTotal());
        userList.setCode(0);
        return userList;
    }

    @RequestMapping("/edit.html")
    public String goUserEdit(@RequestParam(value = "id") String id, Model model)
    {
        User user = userService.getById(Integer.valueOf(id));
        model.addAttribute("user", user);
        return "people/user/edit";
    }

    @ResponseBody
    @RequestMapping("/Upload")
    public String getUploadUser(@RequestBody User user) {
        userService.updateById(user);
        return "200";
    }

    @RequestMapping("/add.html")
    public String goUserAdd()
    {
        return "people/user/add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String getAddUser(@RequestBody User user) {
        System.out.println(user);
        user.setIsOut("1");
        user.setVip(0);
        user.setCreateTime(new Date());
        user.setVip(0);
        if (user.getSex().equals("男")){
            user.setSex("1");
        }else {
            user.setSex("2");
        }
        userService.save(user);
        return "200";
    }

    @GetMapping("/countNewUser")
    @ResponseBody
    public List<UserVo> countNewUser() {
        return userService.countNewUser();
    }




    //获取openid
    @ResponseBody
    @RequestMapping("/login")
    public  User getWxUserOpenid(@RequestParam("code") String id) {
        //拼接url
        System.out.println(id);
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=wx4dee4af695b6f1f8");//appid设置
        url.append("&secret=85d0c4f2bfbe3f566ed60e0e41c5fe49");//secret设置
        url.append("&js_code=");//code设置
        url.append(id);
        url.append("&grant_type=authorization_code");
        Map<String, Object> map = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();//构建一个Client
            HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
            HttpResponse response = client.execute(get);//提交GET请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);
            System.out.println(content);//打印返回的信息
            JSONObject jsonObject = JSONObject.parseObject(content);
            String openid = jsonObject.getString("openid");
            System.out.println(openid);
            user.setOpenKey(openid);
            QueryWrapper<User> Wrapper = new QueryWrapper<>();
            Wrapper.eq("open_key",openid);
            User one = userService.getOne(Wrapper);
            System.out.println(one);
            if (one!=null){
                return one;
            }else{
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @ResponseBody
    @RequestMapping("/AddUser")
    public String  addUser(@RequestBody User user){
        user.setVip(0);
        user.setIsOut("1");
        user.setCreateTime(new Date());
        System.out.println(user);
        userService.save(user);
        return "success";
    }


//    @ResponseBody
//    @RequestMapping("/login/WeChatLogin/AddUser")
//    public User getWeChatLoginAddUser(@RequestBody User user)   {
//        QueryWrapper<User> Wrapper = new QueryWrapper<>();
//        Wrapper.eq("phone",user.getPhone());
//        User one = userService.getOne(Wrapper);
//        System.out.println(user);
//        System.out.println(one);
//        if (one!=null){
//            one.setOpenKey(user.getOpenKey());
//            userService.updateById(one);
//
//            return one;
//        }else{
//            user.setIsOut("1");
//            user.setCreateTime(new Date());
//            userService.save(user);
//            Wrapper.eq("phone",user.getPhone());
//            User one1 = userService.getOne(Wrapper);
//            return one1;
//        }
//    }



}

