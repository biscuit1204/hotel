package top.lumos04.hotel.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lumos04.hotel.pojo.Note;
import top.lumos04.hotel.pojo.NoteList;
import top.lumos04.hotel.service.NoteService;

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
@RequestMapping("/note")
public class NoteController {

    @Autowired
    Note note;
    @Autowired
    NoteService noteService;
    @Autowired
    NoteList noteList;

    @RequestMapping("/table.html")
    public String goNoteTable(){
        return "book/note/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public NoteList getAdminList(@RequestParam(value = "page") Integer pn,
                                  @RequestParam(value = "limit") Integer limit){
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
    @RequestMapping("/getSearchList")
    public NoteList getNoteSearchList(@RequestParam(value = "page") Integer pn,
                                        @RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "result") String result){
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<Note> pages = new Page<>(pn, limit);
        QueryWrapper<Note> Wrapper = new QueryWrapper<>();
        System.out.println(jsonObject);
        if (jsonObject.getString("id")!=null) {
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
                              @RequestParam(value = "id") String id){

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
    public String goAdminEdit(@RequestParam(value = "id") String id, Model model)
    {
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
    public String goAdminAdd()
    {
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

}

