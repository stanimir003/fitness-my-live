package com.example.demo.controler;

import com.example.demo.Repositorys.FoodsRepository;
import com.example.demo.dao.FoofDao;
import com.example.demo.dao.SesionManeger;
import com.example.demo.dao.UserDao2;
import com.example.demo.pojo.Food;
import com.example.demo.pojo.UserFood;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class FoodsControler {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    private FoodsRepository productRepository;

    @GetMapping(value = "/foods/all")
    public List<Food> getAll() {
        return productRepository.findAll();
    }
    @Autowired
    UserDao2 dao2;
    @GetMapping(value = "/foods/fruits")
    public List<Food> getFruits(){
        return dao2.getFoods("fruits");
    }
    @GetMapping(value = "/foods/poultry-meat-and-products")
    public List<Food> getPoultryMeatAndProducts(){
        return dao2.getFoods("poultry meat and products");
    }
    @GetMapping(value = "/foods/fish-seafood-and-products")
    public List<Food> getFishSeafoodAndProducts(){
        return dao2.getFoods("fish, seafood and products");
    }
    @GetMapping(value = "/foods/beef")
    public List<Food> getBeef(){
        return dao2.getFoods("beef");
    }
    @GetMapping(value = "/foods/dairy-productsef")
    public List<Food> getDairyProducts(){
        return dao2.getFoods("bdairy productseef");
    }
    @GetMapping(value = "/foods/pulses-and-tubers")
    public List<Food> getPulsesAndTubers(){
        return dao2.getFoods("pulses and tubers");
    }
    @GetMapping(value = "/foods/pasta-and-bread")
    public List<Food> getPastaAndBread(){
        return dao2.getFoods("pasta and bread");
    }
    @GetMapping(value = "/foods/vegetables")
    public List<Food> getVegetables(){
        return dao2.getFoods("vegetables");
    }
    @GetMapping(value = "/foods/nuts")
    public List<Food> getNuts(){
        return dao2.getFoods("nuts");
    }
    @GetMapping(value = "/foods/spices")
    public List<Food> getSpices(){
        return dao2.getFoods("spices");
    }

    @PutMapping (value = "/add")
    public void addFood(@RequestBody Food food) {
        productRepository.save(food);
    }
    @PostMapping (value = "/addFoodToUser")
    public void addUserFood(HttpServletRequest req,
                            HttpServletResponse resp,
                            @RequestParam ("food") String food,
                            @RequestParam ("grams") Integer grams) throws IOException {
        if (!SesionManeger.validateLogin(req)){
            resp.sendRedirect(req.getContextPath() + "/loggin.html");
        }else {
            try {
                FoofDao.addFood(req,resp,food,grams);
                resp.sendRedirect(req.getContextPath() + "/AddFood.html");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    @Autowired
    FoofDao foofDao;
    @GetMapping(value = "/myFood")
    public List<UserFood> myFood( HttpServletRequest req,HttpServletResponse resp) throws IOException {
        if (!SesionManeger.validateLogin(req)){
            resp.sendRedirect(req.getContextPath() + "/loggin.html");
        }
        String name = SesionManeger.getUser(req);
        return foofDao.getMyFood(name);
    }
    }



