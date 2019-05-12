package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "food")
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    int grams;
    @Column
    String name;
    @Column
    int Kcal;
    @Column
    double protein;
    @Column
    double fat;
    @Column
    double carbohydrates;
    @Column
    int categoryFOOD;

    public Food(String name, int kcal, double protein, double fat, double carbohydrates) {
        this.name = name;
        Kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }


}
