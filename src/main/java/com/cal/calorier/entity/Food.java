package com.cal.calorier.entity;
import javax.persistence.*;
@Entity
@Table(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "foodname")
    String foodname;
    @Column(name = "ingredient")
    String ingredient;
    @Column(name = "calorie")
    double calorie;//以g为单位
    @Column(name = "information")
    String information;
    @Column(name = "link")
    String link;

    public Food() {
    }

    public Food(String foodname, String ingredient, double calorie, String information, String link) {
        this.foodname = foodname;
        this.ingredient = ingredient;
        this.calorie = calorie;
        this.information = information;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodname='" + foodname + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", calorie=" + calorie +
                ", information='" + information + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}