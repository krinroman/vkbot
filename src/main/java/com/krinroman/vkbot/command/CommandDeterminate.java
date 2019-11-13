package com.krinroman.vkbot.command;

import java.util.Collection;

public class CommandDeterminate {
    public static Command getCommand(Collection<Command> commands, String nameCommand) {
        for (Command command : commands) {
            if (command.getName().equals(nameCommand))
                return command;
        }
        return null;
    }
    public static String StringToCommandString(String str){
        switch(str){
            case "погода":
                return "weather";
            case "картинка":
            case "изображение":
                return "image";
            default:
                return null;
        }
    }
}
