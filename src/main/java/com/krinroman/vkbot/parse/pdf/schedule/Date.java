package com.krinroman.vkbot.parse.pdf.schedule;


public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) throws IllegalArgumentException {
        if(day < 0)
            throw new IllegalArgumentException("Неверное указание дня");
        if(month > 12 || month < 0)
            throw new IllegalArgumentException("Неверное указание месяца");
        if(year < 0)
            throw new IllegalArgumentException("Неверное указание года");
        if(month == 2) {
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) {
                        if (day > 29)
                            throw new IllegalArgumentException("Неверное указание дня");
                    } else {
                        if (day > 28)
                            throw new IllegalArgumentException("Неверное указание дня");
                    }
                } else {
                    if (day > 29)
                        throw new IllegalArgumentException("Неверное указание дня");
                }
            }
            else{
                if (day > 28)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
        }

        if(month <= 7 && month != 2){
            if(month % 2 == 0){
                if(day > 30)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
            else{
                if(day > 31)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
        }

        if(month > 7){
            if(month % 2 == 0){
                if(day > 31)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
            else{
                if(day > 30)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String string, String separator) throws IllegalArgumentException {
        if(separator.length() == 0)
            throw new IllegalArgumentException("Разделитель не может быть пустой");
        string = string.replace(separator,"-");
        String[] args = string.split("-");
        if(args.length != 3)
            throw new IllegalArgumentException("Неверный формат строки");
        int dayTemp = Integer.parseInt(args[0]);
        int monthTemp = Integer.parseInt(args[1]);
        int yearTemp = Integer.parseInt(args[2]);
        if(yearTemp < 100) yearTemp += 2000;

        if(dayTemp < 0)
            throw new IllegalArgumentException("Неверное указание дня");
        if(monthTemp > 12 || monthTemp < 0)
            throw new IllegalArgumentException("Неверное указание месяца");
        if(yearTemp < 0)
            throw new IllegalArgumentException("Неверное указание года");
        if(monthTemp == 2) {
            if (yearTemp % 4 == 0) {
                if (yearTemp % 100 == 0) {
                    if (yearTemp % 400 == 0) {
                        if (dayTemp > 29)
                            throw new IllegalArgumentException("Неверное указание дня");
                    } else {
                        if (dayTemp > 28)
                            throw new IllegalArgumentException("Неверное указание дня");
                    }
                } else {
                    if (dayTemp > 29)
                        throw new IllegalArgumentException("Неверное указание дня");
                }
            }
            else{
                if (dayTemp > 28)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
        }

        if(monthTemp <= 7 && monthTemp != 2){
            if(monthTemp % 2 == 0){
                if(dayTemp > 30)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
            else{
                if(dayTemp > 31)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
        }

        if(monthTemp > 7){
            if(monthTemp % 2 == 0){
                if(dayTemp > 31)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
            else{
                if(dayTemp > 30)
                    throw new IllegalArgumentException("Неверное указание дня");
            }
        }

        this.day = dayTemp;
        this. month = monthTemp;
        this.year = yearTemp;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString(String separator){
        String stringDay = Integer.toString(day);
        String stringMonth = Integer.toString(month);
        String stringYear = Integer.toString(year);
        if(day < 10) stringDay = "0" + stringDay;
        if(month < 10) stringMonth = "0" + stringMonth;
        return stringDay + separator + stringMonth + separator + stringYear;
    }

    @Override
    public String toString() {
        String stringDay = Integer.toString(day);
        String stringMonth = Integer.toString(month);
        String stringYear = Integer.toString(year);
        if(day < 10) stringDay = "0" + stringDay;
        if(month < 10) stringMonth = "0" + stringMonth;
        return stringDay + "." + stringMonth + "." + stringYear;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Date){
            Date date = (Date) obj;
            return this.getDay() == date.getDay() &&
                    this.getMonth() == date.getMonth() &&
                    this.getYear() == date.getYear();
        }
        return false;
    }

    public boolean isMore(Date date){
        if(this.getYear() > date.getYear()) return true;
        if(this.getYear() < date.getYear()) return false;
        if(this.getMonth() > date.getMonth()) return true;
        if(this.getMonth() < date.getMonth()) return false;
        if(this.getDay() > date.getDay()) return true;
        if(this.getDay() < date.getDay()) return false;
        return true;
    }
}
