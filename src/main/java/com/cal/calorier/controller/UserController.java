package com.cal.calorier.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cal.calorier.entity.User;
import com.cal.calorier.service.RecordService;
import com.cal.calorier.service.UserService;
import com.cal.calorier.util.EmailUtil;
import com.cal.calorier.util.JsonUtil;
import com.cal.calorier.util.TokenUtil;
import org.apache.commons.mail.HtmlEmail;
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
  @Autowired private UserService userService;
  // 1.注册用户
  @RequestMapping(value = "/users", method = RequestMethod.POST)
  @ResponseBody
  public JSON registerUser(
      @RequestParam("userName") String username,
      @RequestParam("userEmail") String email,
      @RequestParam("userPhone") String phone,
      @RequestParam("userPassword") String password,
      @RequestParam("usercreateTime") String createtime,
      @RequestParam("userAvatar") String avatar) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      if (username != null && password != null) {
        List<User> users = userService.getUserNameList(username);
        if (users.size() != 0) {
          return jsonUtil.messagetoJson("fail", "name_existed", null);
        } else {
          userService.addUser(username, password, phone, email, avatar, createtime);
          return jsonUtil.messagetoJson("success", "register successfully", null);
        }
      } else return null;
    } catch (Exception e) {
      return jsonUtil.messagetoJson("fail", "register_fail", null);
    }
  }
  // 2.用户登录
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public JSON loginUser(
      @RequestParam("userName") String username, @RequestParam("userPassword") String password) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      if (username != null && password != null) {
        List<User> users = userService.getUserNameList(username);
        if (users.size() == 0) {
          return jsonUtil.messagetoJson("fail", "name_not_found", null);
        } else {
          if (users.get(0).getPassword().equals(password)) {
            JSONObject jsonObject = new JSONObject();
            // for token
            User user = new User(username, password);
            ;
            String token = TokenUtil.sign(user);
            JSONObject tokenJson = new JSONObject();
            tokenJson.put("token", token);
            return jsonUtil.messagetoJson("success", "login successfully", tokenJson);
          } else {
            return jsonUtil.messagetoJson("fail", "password_error", null);
          }
        }
      } else return null;
    } catch (Exception e) {
      return jsonUtil.messagetoJson("fail", "login_fail", null);
    }
  }
  // 3.获取用户信息
  @RequestMapping(value = "/info", method = RequestMethod.GET)
  @ResponseBody
  public JSON getUserInfo(@RequestParam("userName") String username) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      List<User> users = userService.getUserNameList(username);
      if (users.size() == 0) {
        return jsonUtil.messagetoJson("fail", "name_not_found", null);
      } else {
        String email = users.get(0).getEmail();
        String avatar = users.get(0).getAvatar();
        String phone = users.get(0).getPhone();
        String createtime = users.get(0).getCreatetime();

        JSONObject userinfo = new JSONObject();

        userinfo.put("user-name", username);
        userinfo.put("user-email", email);
        userinfo.put("user-avatar", avatar);
        userinfo.put("user-phone", phone);
        userinfo.put("user-createtime", createtime);
        return jsonUtil.messagetoJson("success", "get successfully", userinfo);
      }
    } catch (Exception e) {
      return jsonUtil.messagetoJson("fail", "get fail", null);
    }
  }
  // 4.修改密码
  @RequestMapping(value = "/password", method = RequestMethod.PATCH)
  @ResponseBody
  public JSON changePassword(
      @RequestParam("userName") String username,
      @RequestParam("oldpassword") String oldpassword,
      @RequestParam("newpassword") String newpassword) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      List<User> users = userService.getUserNameList(username);
      if (users.size() == 0) {
        return jsonUtil.messagetoJson("fail", "name_not_found", null);
      } else {
        if (users.get(0).getPassword() != oldpassword) {
          return jsonUtil.messagetoJson("fail", "old_password_wrong", null);
        } else {
          userService.changePassword(username, newpassword);
          return jsonUtil.messagetoJson("success", "change successfully", null);
        }
      }
    } catch (Exception e) {
      return jsonUtil.messagetoJson("fail", "change fail", null);
    }
  }
  // 5.修改用户信息
  @RequestMapping(value = "/info", method = RequestMethod.PATCH)
  @ResponseBody
  public JSON changeuserinfo(
      @RequestParam("userName") String username,
      @RequestParam("email") String eamil,
      @RequestParam("avatar") String avatar,
      @RequestParam("phone") String phone) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
      List<User> users = userService.getUserNameList(username);
      if (users.size() == 0) {
        return jsonUtil.messagetoJson("fail", "name_not_found", null);
      } else {
        userService.changeUserInfo(username, eamil, avatar, phone);
        return jsonUtil.messagetoJson("success", "change successfully", null);
      }
    } catch (Exception e) {
      return jsonUtil.messagetoJson("fail", "change fail", null);
    }
  }
  // 6.获取验证码
  @RequestMapping(value = "/verifiCode", method = RequestMethod.PATCH)
  @ResponseBody
  public JSON verifiCode(@RequestParam("email") String email) {
    JsonUtil jsonUtil = new JsonUtil();
    try {
        //随机验证码
      EmailUtil emailUtil = new EmailUtil();
      String code = emailUtil.achieveCode();

      HtmlEmail sent_email = new HtmlEmail();
      sent_email.setHostName("smtp.163.com");
      sent_email.setCharset("utf-8"); // 设置发送的字符类型
      sent_email.addTo(email);
      sent_email.setFrom("xxx@163.com", "xxx");
      sent_email.setAuthentication("xxx@163.com", "xxx4");
      sent_email.setSubject("验证邮箱");
      sent_email.setMsg("尊敬的用户您好,您本次的验证码是:" + code); // 设置发送内容
      sent_email.send(); // 进行发送
        JSONObject codejson=new JSONObject();
        codejson.put("verifiCode",code);
      return jsonUtil.messagetoJson("success", "sent successfully", codejson);
    } catch (Exception e) {
      e.printStackTrace();
      return jsonUtil.messagetoJson("fail", "sent failed", null);
    }
  }
}