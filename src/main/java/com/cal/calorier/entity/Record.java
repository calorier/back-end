package com.cal.calorier.entity;
import javax.persistence.*;
@Entity
@Table(name="record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "time")
    String time;
    @Column(name = "isdelete")
    int isdelete;//0为未删除，1为删除
    //多对一
    @ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
    @JoinColumn(name="userid",referencedColumnName="id")
    private User user;
    //多对一
    @ManyToOne(targetEntity=Food.class,fetch=FetchType.LAZY)
    @JoinColumn(name="foodid",referencedColumnName="id")
    private Food food;

    public Record() {
    }

    public Record(String time, int isdelete, User user, Food food) {
        this.time = time;
        this.isdelete = isdelete;
        this.user = user;
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", isdelete=" + isdelete +
                ", user=" + user +
                ", food=" + food +
                '}';
    }
}