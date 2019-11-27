package com.krinroman.vkbot.command.commands;

import com.krinroman.vkbot.command.Command;
import com.krinroman.vkbot.parse.pdf.MyPDFParser;
import com.krinroman.vkbot.vk.VKManager;

import java.io.IOException;

public class Schedule extends Command {

    public Schedule(String name) {
        super(name);
    }

    @Override
    public void exec(int peerId, String message) {
        try {
            new VKManager().sendMessage(
                    MyPDFParser.ParseSchedule(
                            "https://www.vyatsu.ru/reports/schedule/Group/13173_1_25112019_08122019.pdf")
                            .toString(),
                    peerId);
        } catch (IOException e) {
            new VKManager().sendMessage("Не удалось отобразить рассписание",peerId);
            e.printStackTrace();
        }
    }
}
