package com.krinroman.vkbot.vk;

import com.krinroman.vkbot.request.Post;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.FileNotFoundException;

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

    public void sendImage(String msg, String url, int peerId) throws ClientException, ApiException, FileNotFoundException {
        String attachmentId = Post.SendImagePostVK(vkCore.getVk(),vkCore.getActor(),url);
        vkCore.getVk().messages().send(vkCore.getActor()).peerId(peerId).attachment(attachmentId).message(msg).execute();
    }
}
