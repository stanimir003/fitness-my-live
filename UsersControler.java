package com.example.demo.controler;

import com.example.demo.Repositorys.UserRepository;

import com.example.demo.dao.SesionManeger;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class UsersControler {

    @Autowired
    private UserRepository userRepository;



    @PostMapping (value = "/register")
    public void addUser(@RequestParam("username") String username,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletResponse resp,
                        HttpServletRequest req) throws IOException {
        User user = new User(username,email,password);
        if (!userRepository.existsByNameAndEmail(username,email)) {
            userRepository.save(user);
            SesionManeger.logUser(req);
        }else {
            resp.setStatus(400);
            resp.getWriter().append("Username or password already exists");
            return;
        }
    }
    @PostMapping (value = "/login")
    public void login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletResponse resp,
                        HttpServletRequest req) throws IOException {
        if (userRepository.existsByNameAndPassword(username,password)) {
            SesionManeger.logUser(req);
            SesionManeger.setUser(req,username);
            resp.getWriter().print("bravo logna se");
        }else {
            resp.setStatus(400);
            resp.getWriter().append("name or password does not match");
            resp.sendRedirect(req.getContextPath() + "/loggin.html");
        }
    }
    @GetMapping (value = "/LogOut")
    public void Logout(HttpServletRequest req,
                       HttpServletResponse resp){
        try {
            SesionManeger.logout(req);
            resp.sendRedirect(req.getContextPath() + "/start.html");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    @GetMapping(value = "/images" ,produces = MediaType.IMAGE_GIF_VALUE)
    public byte[] showPic(HttpServletResponse resp) throws IOException {
        File file = new File("C:/Users/admin/IdeaProjects/SpringDiplomna/src/main/webapp/images/foods/ядки.jpg");
        FileInputStream fis = new FileInputStream(file);
        return fis.readAllBytes();
    }

}
