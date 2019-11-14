package com.krinroman.vkbot.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VKCore {

    private VkApiClient vk;
    private GroupActor actor;

    public VKCore() throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
        //actor = new GroupActor(Integer.parseInt(System.getenv("groupId")), System.getenv("accessToken"));
        actor = new GroupActor(Integer.parseInt("188437919"), "0e9e3ce3430be168d178d0e5835df4a2986ba14a53c3677148875cdeb36d320061d3428f37677f5c02785");
    }

    public VkApiClient getVk() {
        return vk;
    }

    public GroupActor getActor() {
        return actor;
    }
}
