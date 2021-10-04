package com.example.tavern.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tavern.pojo.*;
import com.example.tavern.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
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
@RequestMapping("/estimate")
public class EstimateController {
    @Autowired
    Estimate estimate;
    @Autowired
    EstimateList estimateList;
    @Autowired
    EstimateService estimateService;
    @Autowired
    NoteService noteService;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    HotelService hotelService;
    @Autowired
    List<Estimate> list;
    @Autowired
    List<HotelEstimateVo> hotelEstimateVos;
    @Autowired
    UserService userService;

    @RequestMapping("/table.html")
    public String goAdminTable(){
        return "book/estimate/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public EstimateList getAdminList(@RequestParam(value = "page") Integer pn,
                                  @RequestParam(value = "limit") Integer limit){
        Page<Estimate> pages = new Page<>(pn, limit);
        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin) subject.getPrincipal();
        String wait2 = principal.getWait2();
        QueryWrapper<Estimate> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Wrapper.eq("hotel", wait2);
        Page<Estimate> page = estimateService.page(pages, Wrapper);
        List<Estimate> records = page.getRecords();
        estimateList.setData(records);
        estimateList.setData(page.getRecords());
        estimateList.setCount(page.getTotal());
        estimateList.setCode(0);
        return estimateList;
    }

    @ResponseBody
    @RequestMapping("/getSearchList")
    public EstimateList getAdminSearchList(@RequestParam(value = "page") Integer pn,
                                        @RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "result") String result){
        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin) subject.getPrincipal();
        String wait2 = principal.getWait2();
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<Estimate> pages = new Page<>(pn, limit);
        QueryWrapper<Estimate> Wrapper = new QueryWrapper<>();
        Wrapper.eq("hotel", wait2);
        if (jsonObject.getString("id")!=null) {
            Wrapper.like("id", jsonObject.getString("id"));
        }
        if (jsonObject.getString("hotel") != null) {
            Wrapper.like("hotel", jsonObject.getString("hotel"));
        }
        if (jsonObject.getString("createUser") != null) {
            Wrapper.like("create_user", jsonObject.getString("createUser"));
        }
        if (jsonObject.getString("note") != null) {
            Wrapper.like("note", jsonObject.getString("note"));
        }
        Wrapper.eq("is_out", 1);
        Page<Estimate> page = estimateService.page(pages, Wrapper);
        List<Estimate> records = page.getRecords();
        estimateList.setData(records);
        estimateList.setData(page.getRecords());
        estimateList.setCount(page.getTotal());
        estimateList.setCode(0);
        return estimateList;
    }

    @ResponseBody
    @RequestMapping("/Del")
    public EstimateList DelAdmin(@RequestParam(value = "page") Integer pn,
                              @RequestParam(value = "limit") Integer limit,
                              @RequestParam(value = "id") String id){

        estimate.setId(Integer.valueOf(id));
        estimate.setIsOut("0");
        estimateService.updateById(estimate);
        Page<Estimate> pages = new Page<>(pn, limit);
        QueryWrapper<Estimate> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Estimate> page = estimateService.page(pages, Wrapper);
        List<Estimate> records = page.getRecords();
        estimateList.setData(records);
        estimateList.setData(page.getRecords());
        estimateList.setCount(page.getTotal());
        estimateList.setCode(0);
        return estimateList;
    }

    @ResponseBody
    @RequestMapping("/DelList")
    public EstimateList DelAdminList(@RequestParam(value = "page") Integer pn,
                                  @RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "data") String data
    ) {
        JSONArray tableData = JSONArray.parseArray(data);
        for (Iterator iterator = tableData.iterator(); iterator.hasNext(); ) {
            JSONObject job = (JSONObject) iterator.next();
            estimate.setId(Integer.valueOf(job.get("id").toString()));
            estimate.setIsOut("0");
            estimateService.updateById(estimate);
        }
        Page<Estimate> pages = new Page<>(pn, limit);
        QueryWrapper<Estimate> Wrapper = new QueryWrapper<>();
        Wrapper.eq("is_out", 1);
        Page<Estimate> page = estimateService.page(pages, Wrapper);
        List<Estimate> records = page.getRecords();
        estimateList.setData(records);
        estimateList.setData(page.getRecords());
        estimateList.setCount(page.getTotal());
        estimateList.setCode(0);
        return estimateList;
    }

    @RequestMapping("/edit.html")
    public String goAdminEdit(@RequestParam(value = "id") String id, Model model)
    {
        Estimate estimate = estimateService.getById(Integer.valueOf(id));
        model.addAttribute("estimate", estimate);
        return "book/estimate/edit";
    }

    @ResponseBody
    @RequestMapping("/Upload")
    public String getUploadAdmin(@RequestBody Estimate estimate) {
        estimateService.updateById(estimate);
        return "200";
    }

    @RequestMapping("/add.html")
    public String goAdminAdd()
    {
        return "book/estimate/add";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String getAddAdmin(@RequestBody Estimate estimate) {
        estimate.setIsOut("1");
        estimate.setCreateTime(new Date());
        estimateService.save(estimate);
        return "200";
    }

    @ResponseBody
    @RequestMapping("/addWechat")
    public String getSearchNoteListHis(@RequestBody JSONObject jsonParam){
         int id = Integer.parseInt(jsonParam.getString("id"));
        int value = Integer.parseInt(jsonParam.getString("value"));
        int i=0;
        String con = jsonParam.getString("con");
        Note note = noteService.getById(id);
        Room room = roomService.getById(note.getRoom());
        RoomType roomType = roomTypeService.getById(room.getRoomStyle());
        Hotel hotel = hotelService.getById(roomType.getHotel());
        QueryWrapper<Estimate> Wrapper = new QueryWrapper<>();
        Wrapper.eq("hotel",String.valueOf(hotel.getId()));

        estimate.setContent(con);
        estimate.setNote(String.valueOf(id));
        estimate.setHotel(String.valueOf(hotel.getId()));
        estimate.setCreateUser(note.getCreateUser());
        estimate.setIsOut("1");
        estimate.setCreateTime(new Date());
        estimate.setNum(String.valueOf(value));
        estimateService.save(estimate);
        List<Estimate> list = estimateService.list(Wrapper);
        note.setWait1("0");
        System.out.println(note);
        noteService.updateById(note);
        for (Estimate estimate1 : list) {
            i=i+Integer.valueOf(estimate1.getNum());
        }
        hotel.setAssignmark(String.valueOf(i/list.size()));
        hotelService.updateById(hotel);
        return "1";
    }

    @ResponseBody
    @RequestMapping("/searchEstimate")
    public List<HotelEstimateVo> getSearchHotel1(@RequestBody JSONObject jsonParam){
        hotelEstimateVos.clear();
        int id = Integer.parseInt(jsonParam.getString("id"));
        QueryWrapper<Estimate> Wrapper = new QueryWrapper<>();
        Wrapper.eq("hotel",id);
        List<Estimate> list = estimateService.list(Wrapper);
        for (Estimate estimate1 : list) {
            HotelEstimateVo hotelEstimateVo = new HotelEstimateVo();
            User byId = userService.getById(estimate1.getCreateUser());
            hotelEstimateVo.setEstimate(estimate1);
            hotelEstimateVo.setUser(byId);
            hotelEstimateVos.add(hotelEstimateVo);
        }
        return hotelEstimateVos;
    }

}

