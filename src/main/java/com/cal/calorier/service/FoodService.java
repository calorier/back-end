package com.cal.calorier.service;

import com.cal.calorier.dao.FoodRepository;
import com.cal.calorier.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;
    //添加食物
    public int addFood(String foodname, String ingredient, double calorie, String information, String link){
        Food food=new Food(foodname,ingredient,calorie,information,link);
        try{
            foodRepository.save(food);
            return 0;
            }catch (Exception e){
            return 1;
        }
    }
}
