package com.example.tavern.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tavern.pojo.*;
import com.example.tavern.service.HotelService;
import com.example.tavern.service.NoteService;
import com.example.tavern.service.RoomService;
import com.example.tavern.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zcb
 * @since 2021-08-28
 */
@CrossOrigin
@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    Note note;
    @Autowired
    NoteService noteService;
    @Autowired
    NoteList noteList;
    @Autowired
    NoteVo noteVo;
    @Autowired
    List<NoteVo> noteVoList;
    @Autowired
    List<ListVo> listVoList;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    HotelService hotelService;

    @RequestMapping("/table.html")
    public String goNoteTable() {
        return "book/note/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public NoteList getAdminList(@RequestParam(value = "page") Integer pn,
                                 @RequestParam(value = "limit") Integer limit) {
        Page<Note> pages = new Page<>(pn, limit);
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Note> page = noteService.page(pages, Wrapper);
        List<Note> records = page.getRecords();
        for (Note record : records) {
            if (record.getFlag().equals("0")){
                record.setFlag("未入住");
            }
            if (record.getFlag().equals("1")){
                record.setFlag("入住中");
            }
            if (record.getFlag().equals("2")){
                record.setFlag("订单结束");
            }
        }
        noteList.setData(records);
        noteList.setData(page.getRecords());
        noteList.setCount(page.getTotal());
        noteList.setCode(0);
        return noteList;
    }

    @ResponseBody
    @RequestMapping("/getSearchList")
    public NoteList getNoteSearchList(@RequestParam(value = "page") Integer pn,
                                      @RequestParam(value = "limit") Integer limit,
                                      @RequestParam(value = "result") String result) {
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<Note> pages = new Page<>(pn, limit);
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        System.out.println(jsonObject);
        if (jsonObject.getString("id") != null) {
            Wrapper.like("id", jsonObject.getString("id"));
        }
        if (jsonObject.getString("createUser") != null) {
            Wrapper.like("create_user", jsonObject.getString("createUser"));
        }
        if (jsonObject.getString("room") != null) {
            Wrapper.like("room", jsonObject.getString("room"));
        }
        if (jsonObject.getString("flag") != null) {
            Wrapper.like("flag", jsonObject.getString("flag"));
        }
        Wrapper.eq("is_out", 1);
        Page<Note> page = noteService.page(pages, Wrapper);
        List<Note> records = page.getRecords();
        noteList.setData(records);
        noteList.setData(page.getRecords());
        noteList.setCount(page.getTotal());
        noteList.setCode(0);
        return noteList;
    }

    @ResponseBody
    @RequestMapping("/Del")
    public NoteList DelAdmin(@RequestParam(value = "page") Integer pn,
                             @RequestParam(value = "limit") Integer limit,
                             @RequestParam(value = "id") String id) {

        note.setId(Integer.valueOf(id));
        note.setIsOut("0");
        noteService.updateById(note);
        Page<Note> pages = new Page<>(pn, limit);
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Note> page = noteService.page(pages, Wrapper);
        List<Note> records = page.getRecords();
        noteList.setData(records);
        noteList.setData(page.getRecords());
        noteList.setCount(page.getTotal());
        noteList.setCode(0);
        return noteList;
    }

    @ResponseBody
    @RequestMapping("/DelList")
    public NoteList DelAdminList(@RequestParam(value = "page") Integer pn,
                                 @RequestParam(value = "limit") Integer limit,
                                 @RequestParam(value = "data") String data
    ) {
        JSONArray tableData = JSONArray.parseArray(data);
        for (Iterator iterator = tableData.iterator(); iterator.hasNext(); ) {
            JSONObject job = (JSONObject) iterator.next();
            note.setId(Integer.valueOf(job.get("id").toString()));
            note.setIsOut("0");
            noteService.updateById(note);
        }
        Page<Note> pages = new Page<>(pn, limit);
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Note> page = noteService.page(pages, Wrapper);
        List<Note> records = page.getRecords();
        noteList.setData(records);
        noteList.setData(page.getRecords());
        noteList.setCount(page.getTotal());
        noteList.setCode(0);
        return noteList;
    }

    @RequestMapping("/edit.html")
    public String goAdminEdit(@RequestParam(value = "id") String id, Model model) {
        Note note = noteService.getById(Integer.valueOf(id));
        model.addAttribute("note", note);
        return "book/note/edit";
    }

    @ResponseBody
    @RequestMapping("/Upload")
    public String getUploadAdmin(@RequestBody Note note) {
        noteService.updateById(note);
        return "200";
    }


    @RequestMapping("/add.html")
    public String goAdminAdd() {
        return "book/note/add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String getAddAdmin(@RequestBody Note note) {
        note.setIsOut("1");
        note.setCreateTime(new Date());
        note.setCreateTime(new Date());
        noteService.save(note);
        return "200";
    }

    @ResponseBody
    @RequestMapping("/bookNote")
    public NoteVo getSearchHotel(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam);
        int userid = Integer.parseInt(jsonParam.getString("userid"));
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        Wrapper.eq("create_user", userid);
        List<Note> list = noteService.list(Wrapper);
        for (Note note1 : list) {
            noteVo.setNote(note1);
            String room = note1.getRoom();
        }
        System.out.println(list);
        return noteVo;
    }

    @ResponseBody
    @RequestMapping("/noteList")
    public List<ListVo> getSearchNoteList(@RequestBody JSONObject jsonParam) {
        int id = Integer.parseInt(jsonParam.getString("userid"));
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        Wrapper.eq("flag", "0");
        Wrapper.eq("create_user", id);
        Wrapper.eq("is_out", "1");
        List<Note> list = noteService.list(Wrapper);
        listVoList.clear();
        for (Note note1 : list) {
            ListVo listVo = new ListVo();
            listVo.setNote(note1);
            Room byId1 = roomService.getById(note1.getRoom());
            System.out.println(byId1);
            listVo.setRoom(byId1);
            RoomType byId2 = roomTypeService.getById(byId1.getRoomStyle());
            System.out.println(byId2);
            listVo.setRoomType(byId2);
            Hotel byId = hotelService.getById(Integer.valueOf(byId2.getHotel()));
            System.out.println(byId);
            listVo.setHotel(byId);
            listVoList.add(listVo);
        }
        System.out.println(list);
        return listVoList;
    }

    @ResponseBody
    @RequestMapping("/noteListHis")
    public List<ListVo> getSearchNoteListHis(@RequestBody JSONObject jsonParam) {
        int id = Integer.parseInt(jsonParam.getString("userid"));
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        Wrapper.ne("flag", "0");
        Wrapper.eq("create_user", id);
        Wrapper.eq("is_out", "1");
        List<Note> list = noteService.list(Wrapper);
        listVoList.clear();
        for (Note note1 : list) {
            ListVo listVo = new ListVo();
            listVo.setNote(note1);
            Room byId1 = roomService.getById(note1.getRoom());
            System.out.println(byId1);
            listVo.setRoom(byId1);
            RoomType byId2 = roomTypeService.getById(byId1.getRoomStyle());
            System.out.println(byId2);
            listVo.setRoomType(byId2);
            Hotel byId = hotelService.getById(Integer.valueOf(byId2.getHotel()));
            System.out.println(byId);
            listVo.setHotel(byId);
            listVoList.add(listVo);
        }
        System.out.println(list);
        return listVoList;
    }

    @ResponseBody
    @RequestMapping("/delWechat")
    public String delSearchNoteListHis(@RequestBody JSONObject jsonParam) {
        int id = Integer.parseInt(jsonParam.getString("id"));
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        Note note = noteService.getById(id);
        note.setIsOut("0");
        noteService.updateById(note);
        return "200";
    }
}

