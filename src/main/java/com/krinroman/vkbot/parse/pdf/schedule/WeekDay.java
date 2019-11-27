package com.krinroman.vkbot.parse.pdf.schedule;

public enum WeekDay {
    MONDAY("понедельник"),
    TUESDAY("вторник"),
    WEDNESDAY("среда"),
    THURSDAY("четверг"),
    FRIDAY("пятница"),
    SATURDAY("суббота"),
    SUNDAY("воскресенье");

    private String name;
    WeekDay(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isWeekDay(String string){
        WeekDay [] weekDays = WeekDay.values();
        for(WeekDay weekDay : weekDays){
            if(weekDay.getName() == string) return true;
        }
        return false;
    }

    public static WeekDay getWeekDayOnString(String string){
        WeekDay[] weekDays = WeekDay.values();
        for(WeekDay weekDay : weekDays){
            if(weekDay.getName() == string) return weekDay;
        }
        return null;
    }
}
