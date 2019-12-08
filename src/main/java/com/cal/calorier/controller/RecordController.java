package com.cal.calorier.controller;

import com.alibaba.fastjson.JSON;
import com.cal.calorier.service.FoodService;
import com.cal.calorier.service.RecordService;
import com.cal.calorier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
            @RequestParam("userName") String username,
            @RequestParam("userEmail") String email
    ){

    }
}
