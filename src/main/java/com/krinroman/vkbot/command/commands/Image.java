package com.krinroman.vkbot.command.commands;

import com.krinroman.vkbot.command.Command;
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
            new VKManager().sendImage("Это случайное изображение",
                    "https://million-wallpapers.ru/wallpapers/0/28/291762809215698/tixij-tigr.jpg", peerId);
        } catch (ApiException | ClientException | IOException e) {
            e.printStackTrace();
            new VKManager().sendMessage("Не удалось отправить изображение",peerId);
        }

    }
}
