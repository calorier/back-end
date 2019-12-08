package com.cal.calorier.entity;
import javax.persistence.*;
@Entity
@Table(name="record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "userid")
    int userid;
    @Column(name = "foodid")
    int foodid;
    @Column(name = "time")
    String time;
    @Column(name = "isdelete")
    int isdelete;//0为未删除，1为删除

    public Record() {
    }

    public Record(int userid, int foodid, String time, int isdelete) {
        this.userid = userid;
        this.foodid = foodid;
        this.time = time;
        this.isdelete = isdelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public java.lang.String toString() {
        return "Record{" +
                "id=" + id +
                ", userid=" + userid +
                ", foodid=" + foodid +
                ", time='" + time + '\'' +
                ", isdelete=" + isdelete +
                '}';
    }
}