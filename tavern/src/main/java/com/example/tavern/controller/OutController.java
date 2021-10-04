package com.example.tavern.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.tavern.pojo.Note;
import com.example.tavern.pojo.Room;
import com.example.tavern.service.NoteService;
import com.example.tavern.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
@RequestMapping("/out")
public class OutController {

    @Autowired
    Note note;
    @Autowired
    NoteService noteService;
    @Autowired
    RoomService roomService;

    @RequestMapping("/form-step.html")
    public String goAdminTable(){
        return "out/form-step";
    }

    @ResponseBody
    @RequestMapping("/chack")
    public Note getUploadHotel(@RequestBody JSONObject jsonParam) {
        String noteId = jsonParam.getString("note");
        String name = jsonParam.getString("name");
        note.setId(Integer.valueOf(noteId));
        Note byId = noteService.getById(note);
        if (byId!=null){
            if (byId.getCreateUser().equals(name)&&byId.getFlag().equals("1")){
                return byId;
            }else {
                note.setWait1("404");
                return note;
            }
        }else {
            note.setWait1("404");
            return note;
        }
    }

//    @ResponseBody
//    @RequestMapping("/change")
//    public String getUploadNote(@RequestBody JSONObject jsonParam) {
//        String s1="";
//        String noteId = jsonParam.getString("note");
//        String name = jsonParam.getString("name");
//        note.setId(Integer.valueOf(noteId));
//        Note byId = noteService.getById(note);
//        Room room = roomService.getById(byId.getRoom());
//        Date startTime = byId.getStartTime();
//        Date endTime = byId.getEndTime();
//        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd");
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(startTime);
//        rightNow.add(Calendar.DAY_OF_YEAR,-1);
//        while (!sdFormat.format(rightNow.getTime()).equals(sdFormat.format(endTime))){
//            rightNow.add(Calendar.DAY_OF_YEAR,1);
//            String s = sdFormat.format(rightNow.getTime());
//            s1=s1+","+s+",";
//
//        };
//        String wait1 = room.getWait1();
//        String s11 = wait1.replaceAll(s1, "");
//        room.setWait1(s11);
//        roomService.updateById(room);
//        byId.setFlag("2");
//        noteService.updateById(byId);
//        return "200";
//    }

    @RequestMapping("/change")
    public String getUploadNote(@RequestParam(value = "noteId") Integer noteId,
                                @RequestParam(value = "name") Integer name,
                                Model model) {

        String s1="";

        note.setId(Integer.valueOf(noteId));
        Note byId = noteService.getById(note);
        Room room = roomService.getById(byId.getRoom());
        Date startTime = byId.getStartTime();
        Date endTime = byId.getEndTime();
        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startTime);
        rightNow.add(Calendar.DAY_OF_YEAR,-1);
        while (!sdFormat.format(rightNow.getTime()).equals(sdFormat.format(endTime))){
            rightNow.add(Calendar.DAY_OF_YEAR,1);
            String s = sdFormat.format(rightNow.getTime());
            s1=s1+","+s+",";

        };
        String wait1 = room.getWait1();
        String s11 = wait1.replaceAll(s1, "");
        room.setWait1(s11);
        roomService.updateById(room);
        byId.setFlag("2");
        noteService.updateById(byId);
        return "common/welcome";


//        model.addAttribute("note",byId);
//        System.out.println(noteId);
//        System.out.println(byId.getPrice());
//        return "alipay/index";
    }


}

