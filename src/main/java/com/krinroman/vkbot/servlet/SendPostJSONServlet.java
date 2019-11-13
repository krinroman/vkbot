package com.krinroman.vkbot.servlet;

import com.krinroman.vkbot.request.Post;
import org.json.JSONObject;

import java.io.PrintWriter;


public class SendPostJSONServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("Метод для отправки загружен");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();
        String url = System.getenv("urlWebApp") +  "/vk-request";
        String key = request.getParameter("key");
        if(key == null)
            key = "";
        switch(key){
            case "confirmation":{
                printWriter.println("Отправлен запрос confirmation\n"+
                        "Ответ: "+
                        Post.sendRequestJSON(url, new JSONObject("{ \"type\":\"confirmation\"}")));
            }break;
            default:{
                printWriter.println("Заполните параметр get запроса key");
            }
        }


    }
}