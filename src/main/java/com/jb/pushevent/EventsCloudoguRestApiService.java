package com.jb.pushevent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jb.pushevent.config.PushEventConfigurationStore;
import com.jb.pushevent.dto.Event;
import sonia.scm.net.ahc.AdvancedHttpClient;
import sonia.scm.net.ahc.AdvancedHttpRequestWithBody;
import sonia.scm.net.ahc.AdvancedHttpResponse;

import java.io.IOException;

public class EventsCloudoguRestApiService {

  private String endpointUrl = "http://127.0.0.1:8088/";
  private String token = "";

  private final AdvancedHttpClient httpClient;


  private final ObjectMapper objectMapper = new ObjectMapper();


  public EventsCloudoguRestApiService(AdvancedHttpClient httpClient, PushEventConfigurationStore pushEventConfigurationStore) {
    this.httpClient = httpClient;
    endpointUrl = pushEventConfigurationStore.get().getUrl();
    token = pushEventConfigurationStore.get().getToken();
  }

  private AdvancedHttpRequestWithBody createPutRequest(JsonNode payload) {
    final AdvancedHttpRequestWithBody putRequest = this.httpClient.put(endpointUrl + "event/" + System.currentTimeMillis());
    putRequest.jsonContent(payload);
    putRequest.header("Authorization", "Bearer " + token);
    return putRequest;
  }

  public void sendPush(Event eventDto) throws Exception {
    // Construct Request
    AdvancedHttpRequestWithBody putRequest = createPutRequest(eventDto.toJsonNode());

    final AdvancedHttpResponse putPushResponse;
    try {
      putPushResponse = putRequest.request();
      if (!putPushResponse.isSuccessful()) {
        throw new Exception("Push wasn't successfully transmitted to Endpoint." +
          putPushResponse.getStatus() +
          " " +
          putPushResponse.getStatusText());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
