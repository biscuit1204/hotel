package com.example.tavern.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tavern.pojo.Admin;
import com.example.tavern.pojo.AdminList;
import com.example.tavern.pojo.Hotel;
import com.example.tavern.pojo.Note;
import com.example.tavern.service.AdminService;
import com.example.tavern.service.NoteService;
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
@RequestMapping("/live")
public class LiveController {

    @Autowired
    Note note;
    @Autowired
    NoteService noteService;
    @Autowired
    Note note1;

    @RequestMapping("/form-step.html")
    public String goAdminTable(){
        return "live/form-step";
    }

    @ResponseBody
    @RequestMapping("/chack")
    public Note getUploadHotel(@RequestBody JSONObject jsonParam) {
        String noteId = jsonParam.getString("note");
        String name = jsonParam.getString("name");
        note.setId(Integer.valueOf(noteId));
        Note byId = noteService.getById(note);
        System.out.println("111");
        System.out.println(byId);
        if (byId!=null){
            if (byId.getCreateUser().equals(name)&&byId.getFlag().equals("0")){
                return byId;
            }else {
                note1.setWait1("4041");
                return this.note;
            }
        }else {
            note1.setWait1("4042");
            return note;
        }
    }

    @RequestMapping("/change")
    public String getUploadNote(@RequestParam(value = "noteId") Integer noteId,
                                @RequestParam(value = "name") Integer nameId,
                                Model model) {
        note.setId(Integer.valueOf(noteId));
        Note byId = noteService.getById(note);
        byId.setFlag("1");
        noteService.updateById(byId);
        model.addAttribute("note",byId);
        System.out.println(noteId);
        System.out.println(byId.getPrice());
        return "alipay/index";
    }

}

