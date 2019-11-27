package com.krinroman.vkbot.command.commands;

import com.krinroman.vkbot.command.Command;
import com.krinroman.vkbot.parse.WeatherParser;
import com.krinroman.vkbot.vk.VKManager;

import java.io.IOException;

public class Weather extends Command {

    public Weather(String name) {
        super(name);
    }

    @Override
    public void exec(int peerId, String message) {
        new VKManager().sendMessage(getWeather(), peerId);
    }

    private String getWeather(){
        String weather;
        try {
            weather = new WeatherParser().getWeatherTodayDescription();
        } catch (IOException e) {
            weather = "Не удалось получить погоду";
        }
        return weather;
    }

}
