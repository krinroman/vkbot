package com.krinroman.vkbot.command.commands;

import com.krinroman.vkbot.command.Command;
import com.krinroman.vkbot.parse.HTMLParserVyatsu;
import com.krinroman.vkbot.parse.pdf.MyPDFParser;
import com.krinroman.vkbot.vk.VKManager;
import com.vk.api.sdk.client.VkApiClient;

import java.io.IOException;
import java.net.ConnectException;

public class Schedule extends Command {

    public Schedule(String name) {
        super(name);
    }

    @Override
    public void exec(int peerId, String message) {
        String [] args = message.split(" ");
        String groupName = null;
        if(args.length < 2) groupName = "ФИб-3301-51-00";
        else groupName = args[1];
        String pdfUrl;
        try {
            pdfUrl = HTMLParserVyatsu.getUrlPdfSchedule(groupName);
        } catch (ConnectException | IllegalArgumentException e) {
            new VKManager().sendMessage(e.getMessage(),peerId);
            return;
        }
        try {
            com.krinroman.vkbot.parse.pdf.schedule.Schedule schedule = MyPDFParser.ParseSchedule(pdfUrl);
            new VKManager().sendMessage(schedule.toString(),peerId);

        } catch (IOException e) {
            new VKManager().sendMessage("Не удалось просмотреть рассписание или открыть файл с ним",peerId);
        }

    }
}
