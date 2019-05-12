package com.example.demo.dao;

import com.example.demo.pojo.DBManager;
import com.example.demo.pojo.UserFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Component
public class FoofDao {
    private static FoofDao ourInstance = new FoofDao();

    public static FoofDao getInstance() {
        return ourInstance;
    }
    @Autowired
    public JdbcTemplate jdbcTemplate;
    private FoofDao() {
    }

    public static void addFood(HttpServletRequest req,HttpServletResponse resp,String food,int grams) throws IOException, SQLException {
        if (!SesionManeger.validateLogin(req)){
            resp.sendRedirect(req.getContextPath() + "/loggin.html");
        }else {
            String name = SesionManeger.getUser(req);
            int id = getID(name);
            int idFood = getIDFood(food);

            String sql = "INSERT INTO userfood (id_user,food_id,grams) VALUES (?,?,?)";
            PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.setInt(2, idFood);
            ps.setInt(3, grams);
            ps.executeUpdate();

        }

    }
    public static int getID (String name){
        String sql = "SELECT id FROM user WHERE name ='"+name+"'";
        int i = 0;
        try {
            PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 i = rs.getInt("id");
             }
        }catch (SQLException exp){
            System.out.println(exp.getMessage());
        }
        return i;
    }
    public static int getIDFood (String name){
        String sql = "SELECT id_food FROM food WHERE name ='"+name+"'";
        int i = 0;
        try {
            PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                i = rs.getInt("id_food");
            }
        }catch (SQLException exp){
            System.out.println(exp.getMessage());
        }
        return i;
    }
    public List<UserFood> getMyFood(String name){
        int id = getID(name);
        System.out.println(id);
        String sql = "Select name,A.grams FROM  food  JOIN  userfood as A ON (id_food=food_id) where id_user =" + id;
        return jdbcTemplate.query(sql,(resultSet, i) -> toUserFood(resultSet));
    }
    private UserFood toUserFood(ResultSet resultSet) throws SQLException {
        UserFood userFood = new UserFood(resultSet.getString("name"),
                resultSet.getInt("grams")
                );
        return userFood;
    }
}

