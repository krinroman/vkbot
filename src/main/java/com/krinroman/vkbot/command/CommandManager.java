package com.krinroman.vkbot.command;

import com.krinroman.vkbot.command.commands.Image;
import com.krinroman.vkbot.command.commands.Weather;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CommandManager {
    private static List<String> commandsList = new ArrayList<>();
    static {
        commandsList.add("weather");
        commandsList.add("image");
    }

    public static List<String> getCommandsList() { return commandsList; }

    public static HashSet<Command> getCommands(){
        HashSet<Command> commands = new HashSet<>();
        commands.add(new Weather("weather"));
        commands.add(new Image("image"));
        return commands;
    }
}
