package com.krinroman.vkbot.request;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoUpload;
import com.vk.api.sdk.objects.photos.responses.MessageUploadResponse;
import com.vk.api.sdk.objects.photos.responses.WallUploadResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Post {
    public static String sendRequestJSON(String url, JSONObject json) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new StringEntity(json.toString(), StandardCharsets.UTF_8));

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();

        if (entity != null) {
            InputStream inputStream = entity.getContent();
            Scanner scan = new Scanner(inputStream);
            return scan.nextLine();
        }
        return null;
    }

    public static String SendImagePostVK(VkApiClient vk, GroupActor actor, String url) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        File file = null;
        try {
            file = new File(url);
            builder.addBinaryBody(
                    "file",
                    new FileInputStream(file),
                    ContentType.MULTIPART_FORM_DATA,
                    file.getName()
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Не удалось открыть изображение");
            return null;
        }
        try {
            PhotoUpload serverResponse = vk.photos().getMessagesUploadServer(actor).execute();
            MessageUploadResponse uploadResponse = vk.upload().photoMessage(serverResponse.toString(),file).execute();
            List<Photo> photoList = vk.photos().saveMessagesPhoto(actor, uploadResponse.getPhoto())
                    .server(uploadResponse.getServer())
                    .hash(uploadResponse.getHash()).execute();
            Photo photo = photoList.get(0);
            return "photo" + photo.getOwnerId() + "_" + photo.getId();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
