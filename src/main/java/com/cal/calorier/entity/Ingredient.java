package com.cal.calorier.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "ingredientname")
    String ingredientname;
    @Column(name = "calories")
    String calories;
    //多对多
    @ManyToMany(mappedBy="ingredients")
    private Set<Food> foods = new HashSet<Food>(0);

    public Ingredient() {
    }

    public Ingredient(String ingredientname, String calories) {
        this.ingredientname = ingredientname;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientname='" + ingredientname + '\'' +
                ", calories='" + calories + '\'' +
                '}';
    }
}
