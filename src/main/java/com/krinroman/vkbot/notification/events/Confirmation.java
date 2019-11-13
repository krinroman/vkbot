package com.krinroman.vkbot.notification.events;

import com.krinroman.vkbot.json.JSONHandler;
import com.krinroman.vkbot.notification.Event;

public class Confirmation extends Event {

    public Confirmation(String name) {
        super(name);
    }

    @Override
    public String exec(JSONHandler json) {
        return System.getenv("responseString");
    }
}
