package com.example.tavern.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tavern.pojo.Estimate;
import com.example.tavern.pojo.Hotel;
import com.example.tavern.pojo.HotelList;
import com.example.tavern.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Array;
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
        hotel.setWait1(",");
        hotel.setIsOut("1");
        hotel.setOpenDate(new Date());
        hotel.setAssignmark("10");
        hotelService.save(hotel);
        return "200";
    }

    @ResponseBody
    @RequestMapping("/searchHotel")
    public List<Hotel> getSearchHotel(@RequestBody JSONObject jsonParam){
        QueryWrapper<Hotel> Wrapper = new QueryWrapper<>();
        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd");
        if (!jsonParam.getString("date").equals("")){
            JSONObject date = jsonParam.getJSONObject("date");
            int startDay = Integer.parseInt(date.getString("startDay"));
            int endDay = Integer.parseInt(date.getString("endDay"));
            Date startDate = date.getDate("startDate");
            Date endDate = date.getDate("endDate");
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(startDate);
            rightNow.add(Calendar.DAY_OF_YEAR,-1);
            while (!sdFormat.format(rightNow.getTime()).equals(sdFormat.format(endDate))){
                rightNow.add(Calendar.DAY_OF_YEAR,1);
                String s = sdFormat.format(rightNow.getTime());
            };

            for (int i=startDay;i<endDay;i++){
                String s = String.valueOf(i);

                Wrapper.notLike("wait1",s);
            }
        }
        if (!jsonParam.getString("search").equals("")){
            String search = jsonParam.getString("search");
            Wrapper.like("name",search);
        }
        if (!jsonParam.getString("city").equals("")){
            String city = jsonParam.getString("city");
            Wrapper.like("wait2",city);
        }

        List<Hotel> list = hotelService.list(Wrapper);
        return list;
    }



}

