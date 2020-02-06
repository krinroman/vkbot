package com.krinroman.vkbot.command.commands;

import com.krinroman.vkbot.command.Command;
import com.krinroman.vkbot.vk.VKManager;

public class Help extends Command {
    public Help(String name) {
        super(name);
    }

    @Override
    public void exec(int peerId, String message) {
        new VKManager().sendMessage("" +
                        "Могу отправить случайное изображение: команда \"image [запрос]\"\n" +
                        "Напиши \"изображение\" или \"картинка\" и после этого запрос\n\n"+
                        "Могу показать какое у тебя расписание: команда \"schedule\"\nИли просто напиши \"расписание.\""+
                        "А так же отвечать на некоторые сообщения, напиши и узнаешь)",
                peerId);
    }
}
