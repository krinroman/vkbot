package com.krinroman.vkbot.parse;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.ConnectException;

public class HTMLParserVyatsu {
    public static String getUrlPdfSchedule(String groupName) throws IllegalArgumentException, ConnectException {
        Document doc;
        try {
            Connection connection = Jsoup.connect("https://www.vyatsu.ru/studentu-1/spravochnaya-informatsiya/raspisanie-zanyatiy-dlya-studentov.html");
            connection.timeout(15000);
            connection.ignoreHttpErrors(true);
            connection.maxBodySize(Integer.MAX_VALUE);
            doc = connection.get();
        } catch (IOException e) {
            throw new ConnectException("Не удалось получить данные от сервера");
        }
        Elements elements = doc.select("div.grpPeriod");
        Element myElement = null;

        int i = 0;
        for(Element element:elements){
            if(element.text().equals(groupName)){
                myElement = element;
                i++;
            }
            if(i == 2) break;
        }
        String idGroup;
        if(myElement != null)idGroup = myElement.attr("data-grp_period_id");
        else{
            throw new IllegalArgumentException("Такая группа не найдена");
        }

        elements = doc.select("div.listPeriod[id=listPeriod_" + idGroup + "]");
        elements = elements.get(0).select("a");

        return "https://www.vyatsu.ru" + elements.get(0).attr("href");
    }
}
