package com.example.demo.controler;

import com.example.demo.dao.SesionManeger;
import com.example.demo.pojo.Human;
import org.springframework.http.HttpRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class Calculator {



    @GetMapping(value = "/bmr")
    public double bmr(@RequestParam ("weight") Double weight,
                    @RequestParam ("height") Double height,
                    @RequestParam ("old") Double old,
                    @RequestParam ("kfa") Double kfa,
                    @RequestParam("gender") String gender,
                      HttpServletResponse resp
    )  {

        Human human = new Human(weight,height,old,kfa);
            if (human.getWeight() > 0 && human.getHeight() > 0 && human.getOld() > 0 && human.getKfa() > 0) {
                if (gender.equals("m")) {
                    human.setBMNMan();
                } else {
                    human.setBMNWoman();
                }
            } else {
                resp.setStatus(400);
                try {
                    resp.getWriter().append("Some fields not filled correctly");
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            return human.getBmr();


    }
    @GetMapping(value = "/idealWeight")
    public double dealWeight(@RequestParam("height") Double height,
                             @RequestParam("old") Integer old,
                             @RequestParam("gender") String gender){
        if (height == null||old == null||gender == null){
            return 0;
        }
    Human human = new Human(height,old);
    return human.idealWeight(gender);
    }
    @GetMapping(value = "/idealwater")
    public double idealWater(@RequestParam("weight") Double weight){
        if (weight == null){
            return 0;
        }
        Human human = new Human(weight);
        return human.idealWater();
    }
    @GetMapping(value = "/weight-gain" )
    public void weightGain(@RequestParam ("weight") Double weight,
                           @RequestParam ("height") Double height,
                           @RequestParam ("old") Double old,
                           @RequestParam ("kfa") Double kfa,
                           @RequestParam("gender") String gender,
                           @RequestParam("rise") Integer rise,
                           HttpServletResponse resp,
                           HttpServletRequest req) throws IOException {
        Human human = new Human(weight,height,old,kfa);
        if (!SesionManeger.validateLogin(req)){
            resp.sendRedirect(req.getContextPath() + "/loggin.html");
        }else {
            if (human.getWeight() > 0 && human.getHeight() > 0 && human.getOld() > 0 && human.getKfa() > 0) {
                if (gender.equals("m")) {
                    human.setBMNMan();
                } else {
                    human.setBMNWoman();
                }
            } else {
                resp.setStatus(400);
                try {
                    resp.getWriter().append("Some fields not filled correctly ");
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            double cal = human.getBmr() + rise;
            double protein = (cal * human.getPercentProtein()) / 4;
            double fat = (cal * human.getPercentFat()) / 4;
            double carbohydrates = (cal * human.getPercentCarbohydrates()) / 4;
            try {

                resp.getWriter().print(cal + " - Calories ");
                resp.getWriter().print(protein + " - Protein ");
                resp.getWriter().print(fat + " - Fat ");
                resp.getWriter().print(carbohydrates + " - Carbohydrates ");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @GetMapping(value = "/weight-loss" )
    public void weightLoss (@RequestParam ("weight") Double weight,
                           @RequestParam ("height") Double height,
                           @RequestParam ("old") Double old,
                           @RequestParam ("kfa") Double kfa,
                           @RequestParam("gender") String gender,
                           @RequestParam("rise") Integer rise,
                           HttpServletResponse resp,
                            HttpServletRequest req) throws IOException {
        Human human = new Human(weight,height,old,kfa);
        if (!SesionManeger.validateLogin(req)){
            resp.sendRedirect(req.getContextPath() + "/loggin.html");
        }else {
            if (human.getWeight() > 0 && human.getHeight() > 0 && human.getOld() > 0 && human.getKfa() > 0) {
                if (gender.equals("m")) {
                    human.setBMNMan();
                } else {
                    human.setBMNWoman();
                }
            } else {
                resp.setStatus(400);
                try {
                    resp.getWriter().append("Some fields not filled correctly  ");
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            double cal = human.getBmr() - rise;
            double protein = (cal * human.getPercentProtein()) / 4;
            double fat = (cal * human.getPercentFat()) / 4;
            double carbohydrates = (cal * human.getPercentCarbohydrates()) / 4;
            try {

                resp.getWriter().print(cal + " -  Calories ");
                resp.getWriter().print(protein + " - Protein ");
                resp.getWriter().print(fat + " - Fat ");
                resp.getWriter().print(carbohydrates + " - Carbohydrates ");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
