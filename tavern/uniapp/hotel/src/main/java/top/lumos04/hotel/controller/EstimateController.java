package top.lumos04.hotel.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.lumos04.hotel.pojo.Estimate;
import top.lumos04.hotel.pojo.EstimateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lumos04.hotel.service.EstimateService;

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
@RequestMapping("/estimate")
public class EstimateController {
    @Autowired
    Estimate estimate;
    @Autowired
    EstimateList estimateList;
    @Autowired
    EstimateService estimateService;

    @RequestMapping("/table.html")
    public String goAdminTable(){
        return "book/estimate/table";
    }

    @ResponseBody
    @RequestMapping("/getList")
    public EstimateList getAdminList(@RequestParam(value = "page") Integer pn,
                                  @RequestParam(value = "limit") Integer limit){
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
    @RequestMapping("/getSearchList")
    public EstimateList getAdminSearchList(@RequestParam(value = "page") Integer pn,
                                        @RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "result") String result){
        JSONObject jsonObject = JSONObject.parseObject(result);
        Page<Estimate> pages = new Page<>(pn, limit);
        QueryWrapper<Estimate> Wrapper = new QueryWrapper<>();
        System.out.println(jsonObject);
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
}

