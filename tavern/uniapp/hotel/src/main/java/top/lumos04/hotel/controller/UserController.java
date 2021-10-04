package top.lumos04.hotel.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lumos04.hotel.pojo.User;
import top.lumos04.hotel.pojo.UserList;
import top.lumos04.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcb
 * @since 2021-08-28
 */
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
        user.setCreateTime(new Date());
        user.setVip("0");
        if (user.getSex().equals("男")){
            user.setSex("1");
        }else {
            user.setSex("2");
        }
        userService.save(user);
        return "200";
    }

}

