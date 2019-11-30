package com.cal.calorier.service;

import com.cal.calorier.dao.RecordRepository;
import com.cal.calorier.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;

public class RecordService {
    @Autowired
    RecordRepository recordRepository;
    //添加历史
    public int  addHistory(int userid, int foodid, String time, int isdelete){
        Record record=new Record(userid,foodid,time,isdelete);
        try{
            recordRepository.save(record);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
}
