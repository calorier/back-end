package com.cal.calorier.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "email")
    String email;
    @Column(name = "avatar")
    String avatar;
    @Column(name = "phone")
    String phone;
    @Column(name = "createtime")
    String createtime;
    //一对多(User Record)
    @OneToMany(targetEntity=Record.class)
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private Set<Record> records = new HashSet<Record>(0);

    public User() {
    }

    public User(String username, String password, String email, String avatar, String phone, String createtime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.phone = phone;
        this.createtime = createtime;
    }

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public java.lang.String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}