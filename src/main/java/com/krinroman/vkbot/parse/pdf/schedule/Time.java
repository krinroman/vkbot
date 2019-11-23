package com.krinroman.vkbot.parse.pdf.schedule;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second) throws InvalidArgumentException {
        if(hour > 24 || hour < 0)
            throw new InvalidArgumentException(new String[]{"Неверное указание колличества часов"});
        if(minute > 59 || minute < 0)
            throw new InvalidArgumentException(new String[]{"Неверное указание колличества минут"});
        if(second > 59 || second < 0)
            throw new InvalidArgumentException(new String[]{"Неверное указание колличества секунд"});
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time(String string, String separator) throws InvalidArgumentException {
        if(separator.length() == 0)
            throw new InvalidArgumentException(new String[]{"Разделитель не может быть пустой"});
        String[] args = string.split(separator);
        if(args.length < 2 || args.length > 3)
            throw new InvalidArgumentException(new String[]{"Неверный формат строки"});
        int temp;
        temp = Integer.parseInt(args[0]);
        if(temp > 24 || temp < 0)
            throw new InvalidArgumentException(new String[]{"Неверное указание колличества часов"});
        hour = temp;

        temp = Integer.parseInt(args[1]);
        if(temp > 60 || temp < 0)
            throw new InvalidArgumentException(new String[]{"Неверное указание колличества минут"});
        minute = temp;

        if(args.length == 3){
            temp = Integer.parseInt(args[2]);
            if(temp > 60 || temp < 0)
                throw new InvalidArgumentException(new String[]{"Неверное указание колличества секунд"});
            second = temp;
        }
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        if(second == 0)return hour + ":" + minute;
        return hour + ":" + minute + ":" + second;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Time){
            Time time = (Time) obj;
            return this.getHour() == time.getHour() &&
                    this.getMinute() == time.getMinute() &&
                    this.getSecond() == time.getSecond();
        }
        return false;
    }

    public boolean isMore(Time time){
        if(this.getHour() > time.getHour()) return true;
        if(this.getHour() < time.getHour()) return false;
        if(this.getMinute() > time.getMinute()) return true;
        if(this.getMinute() < time.getMinute()) return false;
        if(this.getSecond() > time.getSecond()) return true;
        if(this.getSecond() < time.getSecond()) return false;
        return true;
    }
}
