package com.example.demo.dao;

import org.springframework.beans.factory.support.SecurityContextProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SesionManeger {

    public static boolean validateLogin(HttpServletRequest req){
        HttpSession session = req.getSession();
        if (session.isNew()){
            return false;
        }
        if (session.getAttribute("logged") == null){
            return false;
        }
        if (session.getAttribute("logged").equals(false)){
            return false;
        }
        return true;
    }
    public static void logUser (HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute("logged",true);
        session.setAttribute("username",true);
    }
    public static void logout(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute("logged",false);
    }
    public static void setUser(HttpServletRequest req,String name){
        HttpSession session = req.getSession();
        session.setAttribute("username",name);
    }
    public static String getUser(HttpServletRequest req){
        HttpSession session = req.getSession();
        return (String) session.getAttribute("username");
    }
}
