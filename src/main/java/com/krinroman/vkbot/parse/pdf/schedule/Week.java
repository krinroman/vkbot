package com.krinroman.vkbot.parse.pdf.schedule;


public class Week {
    private int id;
    private Day [] days;

    public Week(int id, Day[] days) {
        this.id = id;
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public Day[] getDays() {
        return days;
    }
}
