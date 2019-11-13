package com.krinroman.vkbot.servlet;

import com.krinroman.vkbot.request.Post;

import java.io.File;
import java.io.PrintWriter;

public class TestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        PrintWriter printWriter = response.getWriter();
        try{
            File file = new File("https://cdn.wallpapersafari.com/63/90/tgQFmO.jpg");
        }
        catch(Exception e){
            printWriter.println("Fail");
            System.out.println("Не удалось получить изображение");
        }
        printWriter.println("Complete");
    }
}