package com.krinroman.vkbot.parse;

import com.krinroman.vkbot.request.Post;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;

public class HTMLParser {

    private Document doc;

    public HTMLParser(String url) throws IOException {
        doc = Jsoup.connect(url).get();
    }

    public static String getUrlImageRandomYandex(String text) throws IOException {
        System.out.println("Запрос на картинку: " + text);
        text = text.trim();
        text.replace(" ","%20");
        Document doc = Jsoup.connect("https://yandex.ru/images/search?text="+ text +"&itype=jpg").get();
        Elements elements = doc.select("a.serp-item__link");
        if (elements.size() <= 0) throw new IOException("Не удалось получить доступ к серверу картинок");
        Random rnd = new Random();
        int i = rnd.nextInt(elements.size());
        String url = elements.get(i).attr("href");
        int index = 0;
        index = url.indexOf("img_url=");
        url = url.substring(index+8);
        index = url.indexOf("&");
        url = url.substring(0,index);
        url = url.replace("%2F","/").replace("%3A",":");
        return url;
    }
}
