package com.cal.calorier.controller;

import com.alibaba.fastjson.JSON;
import com.cal.calorier.entity.Record;
import com.cal.calorier.service.FoodService;
import com.cal.calorier.service.RecordService;
import com.cal.calorier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RecordController {
    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private RecordService recordService;
    //1.上传食物图片
    @RequestMapping(value = "/uploadfood", method = RequestMethod.POST)
    @ResponseBody
    public JSON registerUser(
            @RequestParam("userId") String userid,
            @RequestParam("time") String time,
            @RequestParam("photo") MultipartFile photo
            ){
        if(photo.isEmpty()){
            System.out.println("the photo is empty");
        }
        String photoPath = "E:/idea-workspace/caloriers/foodimg/";
        String fileName= photo.getOriginalFilename();
        String suffixName=fileName.substring(fileName.lastIndexOf("."));//suffix
        fileName= UUID.randomUUID()+suffixName;//new file name
        File dest=new File(photoPath + fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            photo.transferTo(dest);
        }catch (IOException e){
            e.printStackTrace();
        }

//        recordService.addHistory(userid,foodid,time,0);
        //TODO
        //add to record
        return null;
    }


}
