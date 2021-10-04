package com.example.tavern.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcb
 * @since 2021-08-23
 */
@CrossOrigin
@Controller
public class CommonController {

    @ResponseBody
    @RequestMapping("/upload")
    public Map upload(MultipartFile file) throws IOException {
        System.out.println(file.getSize());
        Map map = new HashMap<String,Object>();
        if (!file.isEmpty()){
            String oldName = file.getOriginalFilename();
            String newName = UUID.randomUUID().toString()
                    + oldName.substring(oldName.lastIndexOf("."), oldName.length());
            file.transferTo(new File("C:\\Users\\张晨博\\OneDrive\\桌面\\hotel-改过的\\hotel\\tavern\\src\\main\\resources\\static\\image\\"+newName));
            map.put("code","0");
            map.put("msg","上传成功");
            map.put("name",newName);
            return map;
        }else{
            return map;
        }
    }
}

