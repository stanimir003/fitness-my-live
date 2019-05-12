package com.example.demo.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table ( name = "userfood")
public class UserFood {
    @Column
    String name;
    @Column
    int grams;

    public UserFood(String foodName, int foodGrams) {
        this.name = foodName;
        this.grams = foodGrams;
    }

    @Override
    public String toString() {
        return "UserFood{" +
                "foodName='" + name + '\'' +
                ", foodGrams=" + grams +
                '}';
    }
}


