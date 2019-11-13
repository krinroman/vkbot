package com.krinroman.vkbot.request;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
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
}
