package com.krinroman.vkbot.string;

public class ChangeString {
    public static String RemoveSign(String str){
        StringBuilder stringBuilder = new StringBuilder(str);
        String signStr = ".,/?+-_*\\|:;%$#@!\"\'<>{}[]()";


        for(int i = 0 ; i < signStr.length(); i++){
            char ch = signStr.charAt(i);
            for(int j = 0; j < stringBuilder.length(); i++){
                if(stringBuilder.charAt(j) == ch)stringBuilder.deleteCharAt(j);
            }
        }
        return stringBuilder.toString();
    }
}
