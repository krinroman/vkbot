package com.krinroman.vkbot.parse.pdf.schedule;

import com.sun.javaws.exceptions.InvalidArgumentException;

/*using Class Time in package com.krinroman.vkbot.parse.pdf.schedule.Time*/

public enum LessonTime {
    FIRST(1,"первая","08:20","09:50"),
    SECOND(2,"вторая","10:00","11:30"),
    THIRD(3,"третья","11:45","13:15"),
    FOURTH(4,"четвертая","14:00","15:30"),
    FIFTH(5,"пятая","15:45","17:15"),
    SIXTH(6,"шестая","17:20","18:50"),
    SEVENTH(7,"седьмая","18:55","20:25");

    private Time begin;
    private Time end;
    private String name;
    private int id;

    LessonTime(int id, String name, String timeBegin, String timeEnd) {
        try {
            this.begin = new Time(timeBegin, ":");
            this.end = new Time(timeEnd, ":");
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        this.id = id;
        this.name = name;
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

    public int getId() { return id; }

    public String getIntervalTime(){
        return begin.toString(":") + "-" + end.toString(":");
    }

    public static LessonTime valueOf(int id){
        LessonTime [] lessonTimes = LessonTime.values();
        for(LessonTime lessonTime:lessonTimes){
            if(lessonTime.getId() == id) return lessonTime;
        }
        return null;
    }

    public static LessonTime ParseIntervalTime(String intervalTime){
        LessonTime [] lessonTimes = LessonTime.values();
        for(LessonTime lessonTime:lessonTimes){
            if(intervalTime.equals(lessonTime.getIntervalTime()))return lessonTime;
        }
        return null;
    }

    @Override
    public String toString() {
        return id + " " + begin.toString(":") + "-" + end.toString(":");
    }
}
