package com.example.tavern.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tavern.pojo.Admin;
import com.example.tavern.pojo.AdminList;
import com.example.tavern.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    Admin admin;
    @Autowired
    AdminService adminService;
    @Autowired
    AdminList adminList;

    @RequestMapping("/table.html")
    public String goAdminTable(){
        return "people/admin/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public AdminList getAdminList(@RequestParam(value = "page") Integer pn,
                                @RequestParam(value = "limit") Integer limit){
        Page<Admin> pages = new Page<>(pn, limit);
        QueryWrapper<Admin> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Wrapper.select("id", "name","phone", "create_time","wait1","wait2");
        Page<Admin> page = adminService.page(pages, Wrapper);
        List<Admin> records = page.getRecords();
        adminList.setData(records);
        adminList.setData(page.getRecords());
        adminList.setCount(page.getTotal());
        adminList.setCode(0);
        return adminList;
    }

    @ResponseBody
    @RequestMapping("/getSearchList")
    public AdminList getAdminSearchList(@RequestParam(value = "page") Integer pn,
                                      @RequestParam(value = "limit") Integer limit,
                                      @RequestParam(value = "result") String result){
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<Admin> pages = new Page<>(pn, limit);
        QueryWrapper<Admin> Wrapper = new QueryWrapper<>();
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
        Wrapper.select("id", "name","phone", "create_time");
        Page<Admin> page = adminService.page(pages, Wrapper);
        List<Admin> records = page.getRecords();
        adminList.setData(records);
        adminList.setData(page.getRecords());
        adminList.setCount(page.getTotal());
        adminList.setCode(0);
        return adminList;
    }

    @ResponseBody
    @RequestMapping("/Del")
    public AdminList DelAdmin(@RequestParam(value = "page") Integer pn,
                            @RequestParam(value = "limit") Integer limit,
                            @RequestParam(value = "id") String id){

        admin.setId(Integer.valueOf(id));
        admin.setIsOut("0");
        adminService.updateById(admin);
        Page<Admin> pages = new Page<>(pn, limit);
        QueryWrapper<Admin> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Wrapper.select("id", "name","phone", "create_time");
        Page<Admin> page = adminService.page(pages, Wrapper);
        List<Admin> records = page.getRecords();
        adminList.setData(records);
        adminList.setData(page.getRecords());
        adminList.setCount(page.getTotal());
        adminList.setCode(0);
        return adminList;
    }

    @ResponseBody
    @RequestMapping("/DelList")
    public AdminList DelAdminList(@RequestParam(value = "page") Integer pn,
                                            @RequestParam(value = "limit") Integer limit,
                                            @RequestParam(value = "data") String data
    ) {
        JSONArray tableData = JSONArray.parseArray(data);
        for (Iterator iterator = tableData.iterator(); iterator.hasNext(); ) {
            JSONObject job = (JSONObject) iterator.next();
            admin.setId(Integer.valueOf(job.get("id").toString()));
            admin.setIsOut("0");
            adminService.updateById(admin);
        }
        Page<Admin> pages = new Page<>(pn, limit);
        QueryWrapper<Admin> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Wrapper.select("id", "name","phone", "create_time");
        Page<Admin> page = adminService.page(pages, Wrapper);
        List<Admin> records = page.getRecords();
        adminList.setData(records);
        adminList.setData(page.getRecords());
        adminList.setCount(page.getTotal());
        adminList.setCode(0);
        return adminList;
    }

    @RequestMapping("/edit.html")
    public String goAdminEdit(@RequestParam(value = "id") String id, Model model)
    {
        Admin admin = adminService.getById(Integer.valueOf(id));
        model.addAttribute("admin", admin);
        return "people/admin/edit";
    }

    @ResponseBody
    @RequestMapping("/Upload")
    public String getUploadAdmin(@RequestBody Admin admin) {
        adminService.updateById(admin);
        return "200";
    }

    @RequestMapping("/add.html")
    public String goAdminAdd()
    {
        return "people/Admin/add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String getAddAdmin(@RequestBody Admin admin) {
        admin.setIsOut("1");
        admin.setCreateTime(new Date());
        adminService.save(admin);
        return "200";
    }
}

