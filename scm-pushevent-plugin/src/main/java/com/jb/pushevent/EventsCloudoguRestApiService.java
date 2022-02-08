package com.jb.pushevent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jb.pushevent.dto.Event;
import sonia.scm.net.ahc.AdvancedHttpClient;
import sonia.scm.net.ahc.AdvancedHttpRequestWithBody;
import sonia.scm.net.ahc.AdvancedHttpResponse;

import java.io.IOException;

public class EventsCloudoguRestApiService {

  private final static String URL = "http://127.0.0.1:8088/";

  private final AdvancedHttpClient httpClient;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public EventsCloudoguRestApiService(AdvancedHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  private AdvancedHttpRequestWithBody createPutRequest(JsonNode payload) {
    final AdvancedHttpRequestWithBody postRequest = this.httpClient.put(URL + "event/" + System.currentTimeMillis());
    postRequest.jsonContent(payload);
    return postRequest;
  }

  public void sendPush(Event eventDto) throws Exception {
    // Construct Request
    AdvancedHttpRequestWithBody putRequest = createPutRequest(eventDto.toJsonNode());

    final AdvancedHttpResponse putPushResponse;
    try {
      putPushResponse = putRequest.request();
      if (!putPushResponse.isSuccessful()) {
        throw new Exception("Push wasn't successfully transmitted to events.cloudogu.com! " +
          putPushResponse.getStatus() +
          " " +
          putPushResponse.getStatusText());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
