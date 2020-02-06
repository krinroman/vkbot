package com.krinroman.vkbot.parse.pdf.schedule;

public class Lesson {
    private int timeId;
    private String name;
    private String teacher;
    private String type;
    private int housing;
    private String audience;

    public Lesson(int timeId, String name, String type, String teacher, int housing, String audience) {
        this.timeId = timeId;
        this.name = name;
        this.teacher = teacher;
        this.type = type;
        this.housing = housing;
        this.audience = audience;
    }

    public static Lesson Parse(String string){
        if(string.length() == 12) return null;
        string = BringingToNormalString(string);
        StringBuilder stringBuilder = new StringBuilder(string);
        int timeId = LessonTime.ParseIntervalTime(string.substring(0,11)).getId();
        stringBuilder.delete(0,12);
        String type = "Лекция";
        int index;
        index = stringBuilder.indexOf(type);
        if(index != -1){
            stringBuilder.delete(index-1,index+type.length());
        }
        else{
            type = "Лабораторная работа";
            index = stringBuilder.indexOf(type);
            if(index != -1){
                stringBuilder.delete(index-1,index+type.length());
            }
            else{
                type = "Практическое занятие";
                index = stringBuilder.indexOf(type);
                if(index != -1){
                    stringBuilder.delete(index-1,index+type.length());
                }
            }
        }
        if(index == -1) return new Lesson(timeId, string, null, null, -1, null);
        int indexEnd = index;
        while(indexEnd < stringBuilder.length()){
            if(Character.isDigit(stringBuilder.charAt(indexEnd))) break;
            indexEnd++;
        }
        String teacher = stringBuilder.substring(index,indexEnd).trim();
        stringBuilder.delete(index,indexEnd);
        String [] strings = stringBuilder.substring(index).split("-");
        int housing = Integer.parseInt(strings[0]);
        String audience = strings[1];
        stringBuilder.delete(index-1, stringBuilder.length());
        String name = stringBuilder.toString().trim();
        return new Lesson(timeId,name,type,teacher,housing,audience);
    }

    private static String BringingToNormalString(String string){
        string = string.replace("\n", " ");
        string = string.trim();
        for(int i = 0; i < string.length()-2; i++){
            if(string.charAt(i) == ' ' && string.charAt(i+1) == ' '){
                string = string.substring(0,i+1) + string.substring(i+2);
                i--;
            }
        }
        return string;
    }

    @Override
    public String toString() {
        String returnString = "";
        returnString += LessonTime.valueOf(timeId) + " " + name + " ";
        if(type.equals("Лекция")) returnString += "(Л) ";
        else if(type.equals("Лабораторная работа")) returnString += "(П) ";
        else if(type != null) returnString += type + " ";
        if(teacher != null) returnString += teacher + " ";
        if(housing != -1 && audience != null) returnString += housing + "-" + audience;;
        return returnString;
    }

    public int getTimeId() {
        return timeId;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getType() {
        return type;
    }

    public int getHousing() {
        return housing;
    }

    public String getAudience() {
        return audience;
    }
}
