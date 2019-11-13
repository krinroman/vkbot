package com.krinroman.vkbot.command.commands;

import com.krinroman.vkbot.command.Command;
import com.krinroman.vkbot.vk.VKManager;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public class Image extends Command {

    public Image(String name) {
        super(name);
    }

    @Override
    public void exec(int peerId, String message) {
        try {
            new VKManager().sendImage("Это случайное изображение",
                    "https://cdn.wallpapersafari.com/63/90/tgQFmO.jpg", peerId);
        }
        catch (ClientException | ApiException e) {
            new VKManager().sendMessage("Не удалось отправить изображение", peerId);
        }
    }
}
