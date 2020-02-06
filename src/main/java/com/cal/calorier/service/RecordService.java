package com.cal.calorier.service;

import com.cal.calorier.dao.RecordRepository;
import com.cal.calorier.entity.Food;
import com.cal.calorier.entity.Record;
import com.cal.calorier.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;
    //添加历史
    public int  addHistory(User user, Food food, String time, int isdelete){
        Record record=new Record(time,isdelete,user,food);
        try{
            recordRepository.save(record);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
}
