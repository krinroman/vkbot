package com.krinroman.vkbot;

import com.krinroman.vkbot.parse.pdf.MyPDFParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestMain {
    public static void main(String [] args){
        try {
            MyPDFParser.ParseToHTML("C:\\Users\\rkrin\\Downloads\\timetable.pdf");
            MyPDFParser.ParseToText("C:\\Users\\rkrin\\Downloads\\timetable.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
