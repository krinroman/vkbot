package com.krinroman.vkbot.notification.events;

import com.krinroman.vkbot.json.JSONHandler;
import com.krinroman.vkbot.notification.Event;

public class Unknown extends Event {
    public Unknown(String name) {
        super(name);
    }

    @Override
    public String exec(JSONHandler json) {
        System.out.println("Неизвестный запрос");
        return System.getenv("responseStringDefault");
    }
}
