package com.cal.calorier.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
//    @Column(name = "calorie")
//    double calorie;//以g为单位
    @Column(name = "information")
    String information;
    @Column(name = "link")
    String link;

    //一对多(Food Record)
    @OneToMany(targetEntity=Record.class)
    @JoinColumn(name = "foodid",referencedColumnName = "id")
    private Set<Record> records = new HashSet<Record>(0);

    //多对多(Food Ingredient)
    @ManyToMany
    @JoinTable(name="food_ingre",
            joinColumns= {@JoinColumn(name="foodid",referencedColumnName="id")},
            //用于指定对方表在中间表的字段名称，以及中间表依赖的是它的哪个字段
            inverseJoinColumns= {@JoinColumn(name="ingredientid",referencedColumnName="id")})
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    public Food() {
    }

    public Food(String foodname, String ingredient, double calorie, String information, String link) {
        this.foodname = foodname;
        this.ingredient = ingredient;
//        this.calorie = calorie;
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

//    public double getCalorie() {
//        return calorie;
//    }
//
//    public void setCalorie(double calorie) {
//        this.calorie = calorie;
//    }

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
//                ", calorie=" + calorie +
                ", information='" + information + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}