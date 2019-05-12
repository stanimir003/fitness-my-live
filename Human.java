package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Human {

    private double weight;
    private double height;
    private double old;//
    private double kfa;//коефициент на физическа активност
    double bmr;//калориите за поддържане на телесното си тегло (basal metabolic rate, BMR)
    final double percentProtein = 0.3;
    final double percentFat = 0.2;
    final double percentCarbohydrates = 0.5;
    public Human(double weight, double height, double old, double kfa) {
        this.weight = weight;
        this.height = height;
        this.old = old;
        this.kfa = kfa;
    }

    public Human(double height, double old) {
        this.height = height;
        this.old = old;
    }
    public Human(double weight){
        this.weight = weight;
    }

    public double setBMNMan() {
        this.bmr = this.kfa*( ( 10 * this.weight) + ( 6.25 * this.height) - ( 5 * this.old) - 5);
        return this.bmr;
    }

    public double setBMNWoman() {
        this.bmr = this.kfa*( ( 10 * this.weight) + ( 6.25 * this.height) - ( 5 * this.old) - 161);
        return this.bmr;
    }

    public double idealWeight(String gender){
        double result = 0;
        if (gender.equals("f")) {
            if (this.old > 40) {
                result = getHeight() - 100;
            } else {
                result = getHeight() - 110;
            }
        }else if (gender.equals("m")){
            if (this.old > 40) {
                result = getHeight() - 95;
            } else {
                result = getHeight() - 105;
            }
        }
        if (result < 0){
            return 0;
        }else {
            return result;
        }
    }
    public double idealWater(){
       return  ((this.weight/9)*0.250);
    }

    public double getWeight() {
        return weight;
    }
}


