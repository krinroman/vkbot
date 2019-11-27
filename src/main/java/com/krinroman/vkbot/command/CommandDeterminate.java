package com.krinroman.vkbot.command;

import java.util.Collection;
import com.krinroman.vkbot.string.ChangeString;

public class CommandDeterminate {
    public static Command getCommand(Collection<Command> commands, String nameCommand) {
        for (Command command : commands) {
            if (command.getName().equals(nameCommand))
                return command;
        }
        return null;
    }
    public static String StringToCommandString(String str){
        str = ChangeString.RemoveSign(str);
        switch(str){
            case "погода":
            case "какая сегодня погода":
                return "weather";
            case "картинка":
            case "изображение":
                return "image";
            case "помощь":
            case "что ты умеешь":
                return "help";
            case "расписание":
            case "покажи рассписание":
                return "schedule";
            default:
                return null;
        }
    }
}
