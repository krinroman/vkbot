package com.krinroman.vkbot.parse.pdf.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Day {
    private List<Lesson> lessons = new ArrayList<>(7);
    private WeekDay weekday;
    private Date date;

    public Day Parse(String string){
        return new Day();
    }
}
