package com.krinroman.vkbot.command.commands;

import com.krinroman.vkbot.command.Command;
import com.krinroman.vkbot.vk.VKManager;

public class Image extends Command {

    public Image(String name) {
        super(name);
    }

    @Override
    public void exec(int peerId, String message) {
        new VKManager().sendMessage("Здесь должна быть картинка",peerId);
    }
}
