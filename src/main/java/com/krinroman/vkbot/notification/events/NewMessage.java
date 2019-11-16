package com.krinroman.vkbot.notification.events;

import com.krinroman.vkbot.command.CommandDeterminate;
import com.krinroman.vkbot.command.CommandManager;
import com.krinroman.vkbot.json.JSONHandler;
import com.krinroman.vkbot.notification.Event;
import com.krinroman.vkbot.vk.VKManager;
import com.krinroman.vkbot.parse.MessageParser;

public class NewMessage extends Event {
    public NewMessage(String name) {
        super(name);
    }

    @Override
    public String exec(JSONHandler json) {
        int peerId = json.getVkObject().getInt("peer_id");
        String msg = json.getVkObject().getString("text");
        String cmd = msg.split(" ")[0].toLowerCase();
        if(CommandManager.getCommandsList().contains(cmd))
            CommandDeterminate.getCommand(CommandManager.getCommands(),cmd).exec(peerId,msg);
        else{
            cmd=CommandDeterminate.StringToCommandString(msg.trim());
            if(cmd != null) CommandDeterminate.getCommand(CommandManager.getCommands(),cmd).exec(peerId,msg);
            else new VKManager().sendMessage(MessageParser.ParseMessage(msg),peerId);
        }
        return System.getenv("responseStringDefault");
    }
}
