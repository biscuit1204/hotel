package top.lumos04.hotel.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lumos04.hotel.pojo.Hotel;
import top.lumos04.hotel.pojo.HotelList;
import top.lumos04.hotel.service.HotelService;
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
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    Hotel hotel;
    @Autowired
    HotelService hotelService;
    @Autowired
    HotelList hotelList;

    @RequestMapping("/table.html")
    public String goAdminTable(){
        return "place/hotel/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public HotelList getHotelList(@RequestParam(value = "page") Integer pn,
                                  @RequestParam(value = "limit") Integer limit){
        Page<Hotel> pages = new Page<>(pn, limit);
        QueryWrapper<Hotel> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Hotel> page = hotelService.page(pages, Wrapper);
        List<Hotel> records = page.getRecords();
        hotelList.setData(records);
        hotelList.setData(page.getRecords());
        hotelList.setCount(page.getTotal());
        hotelList.setCode(0);
        return hotelList;
    }

    @ResponseBody
    @RequestMapping("/getSearchList")
    public HotelList getHotelSearchList(@RequestParam(value = "page") Integer pn,
                                        @RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "result") String result){
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<Hotel> pages = new Page<>(pn, limit);
        QueryWrapper<Hotel> Wrapper = new QueryWrapper<>();
        if (jsonObject.getString("id")!=null) {
            Wrapper.like("id", jsonObject.getString("id"));
        }
        if (jsonObject.getString("name") != null) {
            Wrapper.like("name", jsonObject.getString("name"));
        }
        if (jsonObject.getString("assignmark") != null) {
            Wrapper.like("assignmark", jsonObject.getString("assignmark"));
        }
        if (jsonObject.getString("level") != null) {
            Wrapper.like("level", jsonObject.getString("level"));
        }
        Wrapper.eq("is_out", 1);
        Page<Hotel> page = hotelService.page(pages, Wrapper);
        List<Hotel> records = page.getRecords();
        hotelList.setData(records);
        hotelList.setData(page.getRecords());
        hotelList.setCount(page.getTotal());
        hotelList.setCode(0);
        return hotelList;
    }

    @ResponseBody
    @RequestMapping("/Del")
    public HotelList DelHotel(@RequestParam(value = "page") Integer pn,
                              @RequestParam(value = "limit") Integer limit,
                              @RequestParam(value = "id") String id){

        hotel.setId(Integer.valueOf(id));
        hotel.setIsOut("0");
        hotelService.updateById(hotel);
        Page<Hotel> pages = new Page<>(pn, limit);
        QueryWrapper<Hotel> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Hotel> page = hotelService.page(pages, Wrapper);
        List<Hotel> records = page.getRecords();
        hotelList.setData(records);
        hotelList.setData(page.getRecords());
        hotelList.setCount(page.getTotal());
        hotelList.setCode(0);
        return hotelList;
    }

    @ResponseBody
    @RequestMapping("/DelList")
    public HotelList DelHotelList(@RequestParam(value = "page") Integer pn,
                                  @RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "data") String data
    ) {
        JSONArray tableData = JSONArray.parseArray(data);
        for (Iterator iterator = tableData.iterator(); iterator.hasNext(); ) {
            JSONObject job = (JSONObject) iterator.next();
            hotel.setId(Integer.valueOf(job.get("id").toString()));
            hotel.setIsOut("0");
            hotelService.updateById(hotel);
        }
        Page<Hotel> pages = new Page<>(pn, limit);
        QueryWrapper<Hotel> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Hotel> page = hotelService.page(pages, Wrapper);
        List<Hotel> records = page.getRecords();
        hotelList.setData(records);
        hotelList.setData(page.getRecords());
        hotelList.setCount(page.getTotal());
        hotelList.setCode(0);
        return hotelList;
    }

    @RequestMapping("/edit.html")
    public String goHotelEdit(@RequestParam(value = "id") String id, Model model)
    {
        Hotel hotel = hotelService.getById(Integer.valueOf(id));
        model.addAttribute("hotel", hotel);
        return "place/hotel/edit";
    }

    @ResponseBody
    @RequestMapping("/Upload")
    public String getUploadHotel(@RequestBody Hotel hotel) {
        hotelService.updateById(hotel);
        return "200";
    }

    @RequestMapping("/add.html")
    public String goHotelAdd()
    {
        return "place/hotel/add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String getAddHotel(@RequestBody Hotel hotel) {
        hotel.setIsOut("1");
        hotel.setOpenDate(new Date());
        hotel.setAssignmark("10");
        hotelService.save(hotel);
        return "200";
    }
}

