package top.lumos04.hotel.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lumos04.hotel.pojo.Room;
import top.lumos04.hotel.pojo.RoomList;
import top.lumos04.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/room")
public class RoomController {

    @Autowired
    Room room;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomList roomList;

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
        room.setIsOut("1");
        roomService.save(room);
        return "200";
    }


}

