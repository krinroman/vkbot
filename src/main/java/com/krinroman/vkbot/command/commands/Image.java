package com.krinroman.vkbot.command.commands;

import com.krinroman.vkbot.command.Command;
import com.krinroman.vkbot.parse.HTMLParser;
import com.krinroman.vkbot.vk.VKManager;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Image extends Command {

    public Image(String name) {
        super(name);
    }

    @Override
    public void exec(int peerId, String message) {
        try {
            String [] args = message.split(" ");
            String text = "";
            String answer = "Это случайное изображение";
            if(args.length > 1) {
                for(int i = 1; i < args.length; i++)
                    text += args[i] + " ";
                answer += " по запросу: " + text;
            }
            else text = "photo";


            new VKManager().sendImage(answer,
                    HTMLParser.getUrlImageRandomYandex(text), peerId);
        } catch (ApiException | ClientException | IOException e) {
            e.printStackTrace();
            new VKManager().sendMessage("Не удалось отправить изображение",peerId);
        }

    }
}
