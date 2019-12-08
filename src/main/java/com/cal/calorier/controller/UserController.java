package com.cal.calorier.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cal.calorier.entity.User;
import com.cal.calorier.service.RecordService;
import com.cal.calorier.service.UserService;
import com.cal.calorier.util.JsonUtil;
import com.cal.calorier.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/calorier/apis/v1")
public class UserController {
    @Autowired
    private UserService userService;
    //1.注册用户
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public JSON registerUser(
            @RequestParam("userName") String username,
            @RequestParam("userEmail") String email,
            @RequestParam("userPhone") String phone,
            @RequestParam("userPassword") String password,
            @RequestParam("usercreateTime") String createtime,
            @RequestParam("userAvatar") String avatar
            ){
        JsonUtil jsonUtil=new JsonUtil();
        try{
            if (username != null && password != null) {
                List<User> users= userService.getUserNameList(username);
                if (users.size() != 0) {
                    return jsonUtil.messagetoJson("fail","name_existed",null);
                } else {
                    userService.addUser(username,password,phone,email,avatar,createtime);
                    return jsonUtil.messagetoJson("success","register successfully",null);
                }
            }else
                return null;
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","register_fail",null);
        }
    }
    //2.用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSON loginUser(
            @RequestParam("userName") String username,
            @RequestParam("userPassword") String password
    ){
        JsonUtil jsonUtil =new JsonUtil();
        try{
            if (username != null && password != null){
                List<User> users = userService.getUserNameList(username);
                if (users.size() == 0) {
                    return jsonUtil.messagetoJson("fail","name_not_found",null);
                } else {
                    if (users.get(0).getPassword().equals(password)) {
                        JSONObject jsonObject=new JSONObject();
                        //for token
                        User user=new User(username,password);;
                        String token = TokenUtil.sign(user);
                        JSONObject tokenJson=new JSONObject();
                        tokenJson.put("token",token);
                        return jsonUtil.messagetoJson("success","login successfully",tokenJson);
                    } else {
                        return jsonUtil.messagetoJson("fail","password_error",null);
                    }
                }
            }else
                return null;
        }catch (Exception e){
            return jsonUtil.messagetoJson("fail","login_fail",null);
        }
    }

}
