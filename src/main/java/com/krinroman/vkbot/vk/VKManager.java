package com.krinroman.vkbot.vk;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public class VKManager {


    public static VKCore vkCore;

    public VKManager() {
        try {
            vkCore = new VKCore();
        } catch (ApiException | ClientException e) {
            System.out.println("Ошибка в инициализации VKCore");
        }
    }

    public void sendMessage(String msg, int peerId){
        if (msg == null){
            System.out.println("Не удалось, отправить сообщение, т.к. msg = null");
            return;
        }
        try {
            vkCore.getVk().messages().send(vkCore.getActor()).peerId(peerId).message(msg).execute();
            System.out.println("Сообщение отправлено:\npeerId = " + peerId + "\nтекст = " + msg);
        } catch (ApiException | ClientException e) {
            System.out.println("Ошибка отправки сообщения");
        }
    }
}
