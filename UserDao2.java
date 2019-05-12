package com.example.demo.dao;

import com.example.demo.pojo.DBManager;
import com.example.demo.pojo.Food;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class UserDao2 {
    private static UserDao2 ourInstance = new UserDao2();

    public static UserDao2 getInstance() {
        return ourInstance;
    }
    @Autowired
    public JdbcTemplate jdbcTemplate;
    private UserDao2() {
    }
    public void addUser(String name,String email,String pass)  {
        try {
            String sql = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    public boolean getByEmailAndUseraname(String name,String email){
        boolean st = false ;
        try {
            String sql = "SELECT name, email FROM user WHERE name = ? OR email  = ?;";
            PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        }
        catch (Exception e){
            e.getMessage();
        }
        return st;
    }
    public boolean getByPasswordAndUsername(String name,String pass){
        boolean st = false ;
        try {
            String sql = "SELECT name, password FROM user WHERE name = ? and password  = ?;";
            PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        }
        catch (Exception e){
            e.getMessage();
        }
        return st;
    }
    public List<User> getAllUsers() {
//        try(Connection connection = jdbcTemplate.getDataSource().getConnection()){
//            PreparedStatement ps = connection.prepareStatement("");
//            ResultSet rs = ps.executeQuery();
//
//        }
        return jdbcTemplate.query("SELECT  username, email, password FROM user", (resultSet, i) -> toUser(resultSet) );

    }

    private User toUser(ResultSet resultSet) throws SQLException {
        User u = new User(resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("password"));
        return u;
    }

    public List<Food> getFoods(String category){
        String sql = "Select A.name,A.Kcal,A.protein,A.fat,A.carbohydrates FROM  categoryfood inner JOIN  food as A ON (id_foods=categoryFOOD) where category='" + category + "'";
        return jdbcTemplate.query(sql,(resultSet,i) -> toFood(resultSet) );
    }
    private Food toFood(ResultSet resultSet)throws SQLException{
        Food food = new Food(resultSet.getString("name"),
                resultSet.getInt("Kcal"),
                resultSet.getDouble("protein"),
                resultSet.getDouble("fat"),
                resultSet.getDouble("carbohydrates"));
        return food;
    }




}
