package com.krinroman.vkbot.parse.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.krinroman.vkbot.parse.pdf.schedule.*;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyPDFParser {


    public static StringBuilder ParseToText(String fileName) throws IOException {
        PdfReader reader = new PdfReader(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        // не забываем, что нумерация страниц в PDF начинается с единицы.
        for (int i = 1; i <= reader.getNumberOfPages(); ++i) {
            TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
            String text = PdfTextExtractor.getTextFromPage(reader, i, strategy);
            stringBuilder.append(text).append("\n");
        }
        // убираем за собой
        reader.close();
        return stringBuilder;
    }

    public static Schedule ParseSchedule(String fileName) throws IOException {
        return ParseScheduleOnStringBuilder(ParseToText(fileName));
    }

    public static Schedule ParseScheduleOnStringBuilder(StringBuilder stringBuilder){
        int index;
        String exclude = "Министерство науки и высшего образования Российской Федерации\n" +
                "Федеральное государственное бюджетное образовательное учреждение высшего образования\n" +
                "\"Вятский государственный университет\"";
        index = stringBuilder.indexOf(exclude);
        stringBuilder.delete(index, index + exclude.length());
        index = stringBuilder.indexOf("День Интервал");
        stringBuilder.delete(0, index + 14);
        int beginFindIndex = 0;
        while(beginFindIndex != -1){
            index = stringBuilder.indexOf("\n \n", beginFindIndex+1);
            if(index != -1)
                stringBuilder.replace(index, index + 3, "\n");
            beginFindIndex = index;
        }
        beginFindIndex = 0;
        while(beginFindIndex != -1){
            index = stringBuilder.indexOf("\n\n", beginFindIndex+1);
            if(index != -1)
                stringBuilder.replace(index, index + 2, "\n");
            beginFindIndex = index;
        }

        //Начало парсинга рассписания в документе

        index = stringBuilder.indexOf("понедельник");
        String groupName = stringBuilder.substring(0,index);
        stringBuilder.delete(0, index);
        Week[] weeks = new Week[2];
        Day[] days = ParseArrayDay(stringBuilder.substring(0,stringBuilder.lastIndexOf("понедельник")));
        weeks[0] = new Week(1, days);
        days = ParseArrayDay(stringBuilder.substring(stringBuilder.lastIndexOf("понедельник")));
        weeks[1] = new Week(2, days);
        return new Schedule(weeks,groupName);
    }

    private static List<Lesson> ParseListLessons(String string){
        List<Lesson> lessons = new ArrayList<>();
        LessonTime[] lessonTimes = LessonTime.values();
        for(int i = 0; i < lessonTimes.length; i++){
            int indexStart;
            int indexEnd;
            indexStart = string.indexOf(lessonTimes[i].getIntervalTime());
            if(i != lessonTimes.length - 1)
                indexEnd = string.indexOf(lessonTimes[i+1].getIntervalTime());
            else
                indexEnd = string.length();
            Lesson lesson = Lesson.Parse(string.substring(indexStart,indexEnd));
            if(lesson != null) lessons.add(lesson);
        }
        return lessons;
    }

    private static Day [] ParseArrayDay(String string){
        Day [] days = new Day[7];
        WeekDay [] weekDays = WeekDay.values();
        for(int i = 0; i < weekDays.length; i++){
            int indexStart;
            int indexEnd;
            indexStart = string.indexOf(weekDays[i].getName());
            if(i != weekDays.length - 1)
                indexEnd = string.indexOf(weekDays[i+1].getName());
            else indexEnd = string.length();
            String stringLessons = string.substring(string.indexOf("08:20",indexStart),indexEnd);
            String stringDate = string.substring(indexStart + weekDays[i].getName().length()+1,
                                                    string.indexOf("08:20",indexStart)-2);
            try {
                days[i] = new Day(ParseListLessons(stringLessons),
                        weekDays[i],
                        new Date(stringDate,"."));
            } catch (InvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return days;
    }
}
