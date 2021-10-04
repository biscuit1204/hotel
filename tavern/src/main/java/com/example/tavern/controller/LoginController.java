package com.example.tavern.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tavern.pojo.*;
import com.example.tavern.service.HotelService;
import com.example.tavern.service.NoteService;
import com.example.tavern.service.RoomService;
import com.example.tavern.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    HotelService hotelService;
    @Autowired
    RoomService roomService;
    @Autowired
    NoteService noteService;

    //进入网站
    @RequestMapping({"/", "/login", "/login/*", "/login.*"})
    public String goLogin() {
        return "login";
    }

    //登录-重定向
    @PostMapping("/index.html")
    public String toIndex(@Param("username") String username,
                           @Param("password") String password,
                           Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "redirect:/common/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "账号错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    //登录-跳转
    @RequestMapping("/common/index")
    public String goIndex(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin) subject.getPrincipal();
        String name = principal.getName();
        model.addAttribute("name",name);
        if (principal.getWait1()!=""){
            model.addAttribute("wait1",principal.getWait1());
        }else {
            model.addAttribute("wait1","null");
        }

        return "common/index";
    }
    @ResponseBody
    @RequestMapping("/chack")
    public String getUploadHotel() {
        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin) subject.getPrincipal();
        System.out.println(principal);
        return   principal.getWait1();
    }

    //退出登录
    @RequestMapping("/logout")
    public String toLogout(Admin principal) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    //进入首页
    @RequestMapping({   "/common/welcome.html"})
    public String goWelcome(Model model) {
        QueryWrapper<User> WrapperUser = new QueryWrapper<>();
        WrapperUser.eq("is_out","1");
        int countUser = userService.count(WrapperUser);
        model.addAttribute("countUser",countUser);

        QueryWrapper<Hotel> WrapperHotel = new QueryWrapper<>();
        WrapperUser.eq("is_out","1");
        int countHotel = hotelService.count(WrapperHotel);
        model.addAttribute("countHotel",countHotel);

        QueryWrapper<Room> WrapperRoom = new QueryWrapper<>();
        WrapperUser.eq("is_out","1");
        int countRoom = roomService.count(WrapperRoom);
        model.addAttribute("countRoom",countRoom);

        int countNote = noteService.count();
        model.addAttribute("countNote",countNote);
        return "common/welcome";
    }
}
