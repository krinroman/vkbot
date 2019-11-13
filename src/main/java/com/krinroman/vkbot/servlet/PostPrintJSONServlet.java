package com.krinroman.vkbot.servlet;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Set;


public class PostPrintJSONServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("Обработка запроса");
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                System.out.println("Строка: " + line);
                jb.append(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Проиизошла ошибка в получении данных от post");
        }
        System.out.println("Получена строка: "+jb.toString());
        JSONObject json = new JSONObject(jb.toString());

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // create HTML response
        PrintWriter writer = response.getWriter();
        writer.print("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<title>POST</title>" +
                "</head>" +
                "<body>");
        writer.print("<h1>"+"Полученный текст методом пост" + "</h1></br>");
        writer.print(json.toString()+"</br>");
        writer.print(listHtml(json));
        writer.print("</body>" +
                "</html>");
    }

    private String listHtml(JSONObject json) {
        Set<String> keys = json.keySet();
        String result = "<ul>";
        for(String key:keys) {
            if (!IsJsonString(json.get(key).toString()))
                result += "<li>"+ key +" = "+json.get(key).toString()+"</li>";
            else
                result += "<li>" + key + listHtml(new JSONObject(json.get(key).toString())) + "</li>";
        }
        result += "</ul>";
        return result;
    }

    private boolean IsJsonString(String str) {
        try {
            new JSONObject(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {


    }
}