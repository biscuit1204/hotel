package top.lumos04.hotel.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lumos04.hotel.pojo.RoomType;
import top.lumos04.hotel.pojo.RoomTypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lumos04.hotel.service.RoomTypeService;

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
@RequestMapping("/roomType")
public class RoomTypeController {
    @Autowired
    RoomType roomType;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    RoomTypeList roomTypeList;

    @RequestMapping("/table.html")
    public String goRoomTypeTable(){
        return "place/roomType/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public RoomTypeList getRoomTypeList(@RequestParam(value = "page") Integer pn,
                                @RequestParam(value = "limit") Integer limit){
        Page<RoomType> pages = new Page<>(pn, limit);
        QueryWrapper<RoomType> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<RoomType> page = roomTypeService.page(pages, Wrapper);
        List<RoomType> records = page.getRecords();
        roomTypeList.setData(records);
        roomTypeList.setData(page.getRecords());
        roomTypeList.setCount(page.getTotal());
        roomTypeList.setCode(0);
        return roomTypeList;
    }

    @ResponseBody
    @RequestMapping("/getSearchList")
    public RoomTypeList getRoomTypeSearchList(@RequestParam(value = "page") Integer pn,
                                      @RequestParam(value = "limit") Integer limit,
                                      @RequestParam(value = "result") String result){
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<RoomType> pages = new Page<>(pn, limit);
        QueryWrapper<RoomType> Wrapper = new QueryWrapper<>();
        System.out.println(jsonObject);
        if (jsonObject.getString("id")!=null) {
            Wrapper.like("id", jsonObject.getString("id"));
        }
        if (jsonObject.getString("name") != null) {
            Wrapper.like("name", jsonObject.getString("name"));
        }
        if (jsonObject.getString("hotel") != null) {
            Wrapper.like("hotel", jsonObject.getString("hotel"));
        }

        Wrapper.eq("is_out", 1);
        Page<RoomType> page = roomTypeService.page(pages, Wrapper);
        List<RoomType> records = page.getRecords();
        roomTypeList.setData(records);
        roomTypeList.setData(page.getRecords());
        roomTypeList.setCount(page.getTotal());
        roomTypeList.setCode(0);
        return roomTypeList;
    }

    @ResponseBody
    @RequestMapping("/Del")
    public RoomTypeList DelRoomType(@RequestParam(value = "page") Integer pn,
                            @RequestParam(value = "limit") Integer limit,
                            @RequestParam(value = "id") String id){

        roomType.setId(Integer.valueOf(id));
        roomType.setIsOut("0");
        roomTypeService.updateById(roomType);
        Page<RoomType> pages = new Page<>(pn, limit);
        QueryWrapper<RoomType> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<RoomType> page = roomTypeService.page(pages, Wrapper);
        List<RoomType> records = page.getRecords();
        roomTypeList.setData(records);
        roomTypeList.setData(page.getRecords());
        roomTypeList.setCount(page.getTotal());
        roomTypeList.setCode(0);
        return roomTypeList;
    }

    @ResponseBody
    @RequestMapping("/DelList")
    public RoomTypeList DelRoomTypeList(@RequestParam(value = "page") Integer pn,
                                @RequestParam(value = "limit") Integer limit,
                                @RequestParam(value = "data") String data
    ) {
        JSONArray tableData = JSONArray.parseArray(data);
        for (Iterator iterator = tableData.iterator(); iterator.hasNext(); ) {
            JSONObject job = (JSONObject) iterator.next();
            roomType.setId(Integer.valueOf(job.get("id").toString()));
            roomType.setIsOut("0");
            roomTypeService.updateById(roomType);
        }
        Page<RoomType> pages = new Page<>(pn, limit);
        QueryWrapper<RoomType> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<RoomType> page = roomTypeService.page(pages, Wrapper);
        List<RoomType> records = page.getRecords();
        roomTypeList.setData(records);
        roomTypeList.setData(page.getRecords());
        roomTypeList.setCount(page.getTotal());
        roomTypeList.setCode(0);
        return roomTypeList;
    }

    @RequestMapping("/edit.html")
    public String goRoomTypeEdit(@RequestParam(value = "id") String id, Model model)
    {
        RoomType roomType = roomTypeService.getById(Integer.valueOf(id));
        model.addAttribute("roomType", roomType);
        return "place/roomType/edit";
    }

    @ResponseBody
    @RequestMapping("/Upload")
    public String getUploadRoomType(@RequestBody RoomType roomType) {
        System.out.println(roomType);
        roomTypeService.updateById(roomType);
        return "200";
    }

    @RequestMapping("/add.html")
    public String goRoomTypeAdd()
    {
        return "place/roomType/add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String getAddRoomType(@RequestBody RoomType roomType) {
        roomType.setIsOut("1");
        roomTypeService.save(roomType);
        return "200";
    }

}

