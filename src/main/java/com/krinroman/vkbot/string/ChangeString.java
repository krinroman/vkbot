package com.krinroman.vkbot.string;

public class ChangeString {
    public static String RemoveSign(String str){
        String signStr = ".,/?+-_*\\|:;%$#@!\"\'<>{}[]()";

        for(int i = 0; i < signStr.length();i++){
            str = str.replace(signStr.charAt(i),'\0');
        }

    }
}
