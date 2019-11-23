package com.krinroman.vkbot.parse.pdf.schedule;

public class Lesson {
    private int timeId;
    private String name;
    private String teacher;
    private String type;
    private int housing;
    private int audience;

    public Lesson(int timeId, String name, String type, String teacher, int housing, int audience) {
        this.timeId = timeId;
        this.name = name;
        this.teacher = teacher;
        this.type = type;
        this.housing = housing;
        this.audience = audience;
    }

    public static Lesson Parse(String string){

        return new Lesson(0,"","","",0,0);
    }
}
