package com.krinroman.vkbot.parse.pdf.schedule;

import com.sun.javaws.exceptions.InvalidArgumentException;

public enum LessonTime {
    FIRST("первая","8:20","9:50"),
    SECOND("вторая","10:0","11:30"),
    THIRD("третья","11:45","13:15"),
    FOURTH("четвертая","14:0","15:30"),
    FIFTH("пятая","15:45","17:15"),
    SIXTH("шестая","17:20","18:50"),
    SEVENTH("седьмая","18:55","20:25");

    private Time begin;
    private Time end;
    private String name;

    private LessonTime(String name, String timeBegin, String timeEnd) {
        try {
            this.begin = new Time(timeBegin, ":");
            this.end = new Time(timeEnd, ":");
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

    public Time getBegin() {
        return begin;
    }

    public Time getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " пара: " + begin.toString() + " - " + end.toString();
    }
}
