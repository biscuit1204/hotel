package com.example.tavern.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tavern.pojo.*;
import com.example.tavern.service.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@RequestMapping("/room")
public class RoomController {

    @Autowired
    Room room;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomList roomList;
    @Autowired
    RoomType roomType;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    Hotel hotel;
    @Autowired
    HotelService hotelService;
    @Autowired
    Note note;
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;

    @RequestMapping("/table.html")
    public String goRoomTable(){
        return "place/room/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public RoomList getRoomList(@RequestParam(value = "page") Integer pn,
                                        @RequestParam(value = "limit") Integer limit){
        Page<Room> pages = new Page<>(pn, limit);
        QueryWrapper<Room> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Room> page = roomService.page(pages, Wrapper);
        List<Room> records = page.getRecords();
        roomList.setData(records);
        roomList.setData(page.getRecords());
        roomList.setCount(page.getTotal());
        roomList.setCode(0);
        return roomList;
    }

    @ResponseBody
    @RequestMapping("/getSearchList")
    public RoomList getRoomSearchList(@RequestParam(value = "page") Integer pn,
                                              @RequestParam(value = "limit") Integer limit,
                                              @RequestParam(value = "result") String result){
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<Room> pages = new Page<>(pn, limit);
        QueryWrapper<Room> Wrapper = new QueryWrapper<>();
        System.out.println(jsonObject);
        if (jsonObject.getString("id")!=null) {
            Wrapper.like("id", jsonObject.getString("id"));
        }
        if (jsonObject.getString("name") != null) {
            Wrapper.like("name", jsonObject.getString("name"));
        }
        if (jsonObject.getString("roomStyle") != null) {
            Wrapper.like("room_style", jsonObject.getString("roomStyle"));
        }
        if (jsonObject.getString("state") != null) {
            Wrapper.like("state", jsonObject.getString("state"));
        }
        Wrapper.eq("is_out", 1);
        Page<Room> page = roomService.page(pages, Wrapper);
        List<Room> records = page.getRecords();
        roomList.setData(records);
        roomList.setData(page.getRecords());
        roomList.setCount(page.getTotal());
        roomList.setCode(0);
        return roomList;
    }

    @ResponseBody
    @RequestMapping("/Del")
    public RoomList DelRoomType(@RequestParam(value = "page") Integer pn,
                                    @RequestParam(value = "limit") Integer limit,
                                    @RequestParam(value = "id") String id){

        room.setId(Integer.valueOf(id));
        room.setIsOut("0");
        roomService.updateById(room);
        Page<Room> pages = new Page<>(pn, limit);
        QueryWrapper<Room> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Room> page = roomService.page(pages, Wrapper);
        List<Room> records = page.getRecords();
        roomList.setData(records);
        roomList.setData(page.getRecords());
        roomList.setCount(page.getTotal());
        roomList.setCode(0);
        return roomList;
    }

    @ResponseBody
    @RequestMapping("/DelList")
    public RoomList DelRoomTypeList(@RequestParam(value = "page") Integer pn,
                                        @RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "data") String data
    ) {
        JSONArray tableData = JSONArray.parseArray(data);
        for (Iterator iterator = tableData.iterator(); iterator.hasNext(); ) {
            JSONObject job = (JSONObject) iterator.next();
            room.setId(Integer.valueOf(job.get("id").toString()));
            room.setIsOut("0");
            roomService.updateById(room);
        }
        Page<Room> pages = new Page<>(pn, limit);
        QueryWrapper<Room> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Room> page = roomService.page(pages, Wrapper);
        List<Room> records = page.getRecords();
        roomList.setData(records);
        roomList.setData(page.getRecords());
        roomList.setCount(page.getTotal());
        roomList.setCode(0);
        return roomList;
    }

    @RequestMapping("/edit.html")
    public String goRoomTypeEdit(@RequestParam(value = "id") String id, Model model)
    {
        Room room = roomService.getById(Integer.valueOf(id));
        model.addAttribute("room", room);
        return "place/room/edit";
    }

    @ResponseBody
    @RequestMapping("/Upload")
    public String getUploadRoomType(@RequestBody Room room) {
        System.out.println(room);
        roomService.updateById(room);
        return "200";
    }

    @RequestMapping("/add.html")
    public String goRoomAdd()
    {
        return "place/room/add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String getAddRoom(@RequestBody Room room) {
        room.setWait1(" ");
        room.setIsOut("1");
        roomService.save(room);
        return "200";
    }

//    @Test
//    public void getId(){
//         String s1="2021111";
//         String s2="21";
//        String aa = s1.replaceAll(s2, "aa");
//        System.out.println(aa);
//    }


    @ResponseBody
    @RequestMapping("/bookHome")
    public String getSearchHotel(@RequestBody JSONObject jsonParam){
        QueryWrapper<Room> Wrapper = new QueryWrapper<>();
        String s1 = new String();
        int id = Integer.parseInt(jsonParam.getString("id"));
        JSONObject date = jsonParam.getJSONObject("date");
        int startDay = Integer.parseInt(date.getString("startDay"));
        int endDay = Integer.parseInt(date.getString("endDay"));
        int n=0;
        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = date.getDate("startDate");
        Date endDate = date.getDate("endDate");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startDate);
        rightNow.add(Calendar.DAY_OF_YEAR,-1);
        while (!sdFormat.format(rightNow.getTime()).equals(sdFormat.format(endDate))){
            rightNow.add(Calendar.DAY_OF_YEAR,1);
            String s = sdFormat.format(rightNow.getTime());
            s1=s1+","+s+",";
            Wrapper.notLike("wait1",s1);
            n++;
        };
        Wrapper.eq("room_style",jsonParam.getString("id"));
        List<Room> list = roomService.list(Wrapper);
        if (list.size()>0){
            Room room = list.get(0);
            String wait1 = room.getWait1();
            wait1=wait1+s1;
            room.setWait1(wait1);
            roomService.updateById(room);
            RoomType byId = roomTypeService.getById(room.getRoomStyle());
            String userid = jsonParam.getString("userid");
            note.setCreateUser(userid);
            note.setIsOut("1");
            note.setCreateTime(new Date());
            note.setRoom(room.getId().toString());
            note.setDayLong(String.valueOf(n));
            int i = byId.getPrice() * n;
            note.setPrice(String.valueOf(i));
            note.setStartTime(startDate);
            note.setEndTime(endDate);
            note.setFlag("0");
            noteService.save(note);
            User byId1 = userService.getById(Integer.valueOf(userid));
            int vip = byId1.getVip();
            vip=vip+1;
            byId1.setVip(vip);
            userService.updateById(byId1);
            return "预订成功";
        }else {
            roomType.setId(id);
            RoomType byId = roomTypeService.getById(roomType);
            String wait1 = byId.getWait1();
            String hotel = byId.getHotel();
            wait1=wait1+s1;
            roomTypeService.updateById(byId);
            QueryWrapper<RoomType> Wrapper1 = new QueryWrapper<>();
            for (int i=startDay;i<endDay;i++){
                String s = String.valueOf(i);
                s1=s1+","+s+",";
                Wrapper1.notLike("wait1",s);
            }
            List<RoomType> list1 = roomTypeService.list(Wrapper1);
            if (list1.size()>0){
                return "房间已满";
            }else {
                Hotel byId1 = hotelService.getById(hotel);
                String wait11 = byId1.getWait1();
                wait11=wait11+s1;
                hotelService.updateById(byId1);
                return "房间已满";
            }
        }
    }

}

