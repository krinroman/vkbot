package com.krinroman.vkbot.servlet;

import com.krinroman.vkbot.request.Post;
import com.krinroman.vkbot.vk.VKCore;
import com.krinroman.vkbot.vk.VKManager;

import java.io.File;
import java.io.PrintWriter;

public class TestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        PrintWriter printWriter = response.getWriter();
        try{
            VKCore vkCore = new VKCore();
            System.out.println("Началсь отправка");
            Post.SendImagePostVK(vkCore.getVk(),vkCore.getActor(),
                    "https://www.sunhome.ru/i/wallpapers/244/vozvraschenie-navsegda.orig.jpg");
        }
        catch(Exception e){
            e.printStackTrace();
            printWriter.println("Fail");
            System.out.println("Не удалось получить изображение");
            return;
        }
        printWriter.println("Complete");
    }
}