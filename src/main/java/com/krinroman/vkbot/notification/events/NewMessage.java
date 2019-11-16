package com.krinroman.vkbot.notification.events;

import com.krinroman.vkbot.command.CommandDeterminate;
import com.krinroman.vkbot.command.CommandManager;
import com.krinroman.vkbot.command.Commander;
import com.krinroman.vkbot.json.JSONHandler;
import com.krinroman.vkbot.notification.Event;
import com.krinroman.vkbot.vk.VKManager;
import com.krinroman.vkbot.parse.MessageParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewMessage extends Event {
    public NewMessage(String name) {
        super(name);
    }

    @Override
    public String exec(JSONHandler json) {
        int peerId = json.getVkObject().getInt("peer_id");
        String msg = json.getVkObject().getString("text");
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Commander(msg,peerId));
        return System.getenv("responseStringDefault");
    }
}
