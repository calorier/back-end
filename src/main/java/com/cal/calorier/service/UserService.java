package com.cal.calorier.service;

import com.cal.calorier.dao.UserRepository;
import com.cal.calorier.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    //添加用户
    public int addUser(String username, String password, String phone, String email,String avater,String createtime) {
        User user = new User(username,password,email,avater,phone,createtime);
        try{
            userRepository.save(user);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
    //查询所有信息
    public List<User> getAllInfo(){
        List<User> userList = userRepository.findAll();
        return userList;
    }
    //查询用户名列表
    public List<User> getUserNameList(String username){
        List<User> userList = userRepository.findByUsername(username);
        return userList;
    }
    //修改密码
    public void changePassword(String username,String newpassword) {
        userRepository.updateUser(username,newpassword);
    }
    //修改用户信息
    public void changeUserInfo(String username, String eamil, String avatar, String phone){
        userRepository.updateUserInfo(username, eamil, avatar, phone);
    }

}
