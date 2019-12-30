package com.cal.calorier.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cal.calorier.entity.Record;
import com.cal.calorier.entity.User;
import com.cal.calorier.service.FoodService;
import com.cal.calorier.service.RecordService;
import com.cal.calorier.service.UserService;
import com.cal.calorier.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class RecordController {
  @Autowired private UserService userService;
  @Autowired private FoodService foodService;
  @Autowired private RecordService recordService;
  // 1.上传食物图片
  @RequestMapping(value = "/uploadfood", method = RequestMethod.POST)
  @ResponseBody
  public JSON registerUser(
      @RequestParam("userId") String userid,
      @RequestParam("time") String time,
      @RequestParam("photo") MultipartFile photo) {
    JsonUtil jsonUtil = new JsonUtil();
    if (photo.isEmpty()) {
      System.out.println("the photo is empty");
    }
    String photoPath = "E:/idea-workspace/caloriers/foodimg/";
    String fileName = photo.getOriginalFilename();
    String suffixName = fileName.substring(fileName.lastIndexOf(".")); // suffix
    fileName = UUID.randomUUID() + suffixName; // new file name
    File dest = new File(photoPath + fileName);
    if (!dest.getParentFile().exists()) {
      dest.getParentFile().mkdir();
    }
    try {
      photo.transferTo(dest);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //        recordService.addHistory(userid,foodid,time,0);
    return null;
  }
  // 2.获取用户日历史列表
  @RequestMapping(value = "/records/day", method = RequestMethod.GET)
  @ResponseBody
  public JSON getDayRecords(
      @RequestParam("userId") String userid, @RequestParam("time") String time) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      JSONArray dayRecords = new JSONArray();
      return jsonUtil.messagetoJson("success", "get successfully", dayRecords);
    } catch (Exception e) {
      e.printStackTrace();
      return jsonUtil.messagetoJson("fail", "get failed", null);
    }
  }
  // 3.获取用户月历史列表
  @RequestMapping(value = "/records/month", method = RequestMethod.GET)
  @ResponseBody
  public JSON getMonthRecords(
      @RequestParam("userId") String userid, @RequestParam("time") String time) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      JSONArray monthRecords = new JSONArray();
      return jsonUtil.messagetoJson("success", "get successfully", monthRecords);
    } catch (Exception e) {
      e.printStackTrace();
      return jsonUtil.messagetoJson("fail", "get failed", null);
    }
  }
  // 4.获取用户年历史列表
  @RequestMapping(value = "/records/year", method = RequestMethod.GET)
  @ResponseBody
  public JSON getYearRecords(
      @RequestParam("userId") String userid, @RequestParam("time") String time) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      JSONArray yearRecords = new JSONArray();
      return jsonUtil.messagetoJson("success", "get successfully", yearRecords);
    } catch (Exception e) {
      e.printStackTrace();
      return jsonUtil.messagetoJson("fail", "get failed", null);
    }
  }
  // 5.获取用户所有历史列表
  @RequestMapping(value = "/records/all", method = RequestMethod.GET)
  @ResponseBody
  public JSON getAllRecords(@RequestParam("userId") String userid) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      JSONArray allRecords = new JSONArray();
      return jsonUtil.messagetoJson("success", "get successfully", allRecords);
    } catch (Exception e) {
      e.printStackTrace();
      return jsonUtil.messagetoJson("fail", "get failed", null);
    }
  }
  // 6.获取用户单条历史记录
  @RequestMapping(value = "/record", method = RequestMethod.GET)
  @ResponseBody
  public JSON getARecords(@RequestParam("recordId") String recordid) {
      JsonUtil jsonUtil = new JsonUtil();
      try {
          JSONArray aRecord = new JSONArray();
          return jsonUtil.messagetoJson("success", "get successfully", aRecord);
      } catch (Exception e) {
          e.printStackTrace();
          return jsonUtil.messagetoJson("fail", "get failed", null);
      }
  }
}
