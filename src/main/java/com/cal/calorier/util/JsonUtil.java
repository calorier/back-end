package com.cal.calorier.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
    int code;
    String message;
    JSON data;

    public JsonUtil() {
    }

    public JsonUtil(int code, String message, JSON data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSON getData() {
        return data;
    }

    public void setData(JSON data) {
        this.data = data;
    }

    public JSON messagetoJson(String status, String message_type, JSON data){
        JSONObject jsonObject = new JSONObject();
        if(status=="success"){
            jsonObject.put("code",1);
            jsonObject.put("message",message_type);
            jsonObject.put("data",data);
        }else if(status=="fail"){
            jsonObject.put("code",0);
            jsonObject.put("message",message_type);
            jsonObject.put("data",data);
        }
        return jsonObject;
    }
//
    public JSON getuserinfo(String name,String email,String phone,String avatar,String createtime){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user-name",name);
        jsonObject.put("user-email",email);
        jsonObject.put("user-avatar",avatar);
        jsonObject.put("user-phone",phone);
        jsonObject.put("user-createtime",createtime);
        return jsonObject;
    }
}
