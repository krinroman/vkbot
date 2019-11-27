package com.krinroman.vkbot.parse.pdf.schedule;

import java.util.List;

public class Schedule {
    private Week [] weeks;
    private String groupName;

    public Schedule(Week[] weeks, String groupName) {
        groupName = groupName.replace("\n","");
        this.weeks = weeks;
        this.groupName = groupName;
    }

    public Week[] getWeeks() {
        return weeks;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Группа: " + groupName + "\n");
        int countWeek = 1;
        for(Week week:weeks){
            stringBuilder.append(countWeek + " неделя\n");
            Day[] days = week.getDays();
            for(Day day:days){
                List<Lesson> lessons = day.getLessons();
                if(!lessons.isEmpty())
                    stringBuilder.append(day.getWeekDay().getName() + " " + day.getDate().toString() + "\n");
                for(Lesson lesson:lessons){
                    stringBuilder.append(lesson.toString() + "\n");
                }
                if(!lessons.isEmpty())
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n\n");
            countWeek++;
        }
        return stringBuilder.toString();
    }
}
