package com.krinroman.vkbot.parse.pdf.schedule;

import java.util.List;

/*using Class Time in package com.krinroman.vkbot.parse.pdf.schedule.Date*/

public class Day {
    private List<Lesson> lessons;
    private WeekDay weekDay;
    private Date date;

    public Day(List<Lesson> lessons, WeekDay weekDay, Date date) {
        this.lessons = lessons;
        this.weekDay = weekDay;
        this.date = date;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public Date getDate() {
        return date;
    }
}
