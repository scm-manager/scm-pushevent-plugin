package com.jb.pushevent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jb.pushevent.dto.Event;
import com.jb.pushevent.dto.Push;
import sonia.scm.net.ahc.AdvancedHttpClient;
import sonia.scm.net.ahc.AdvancedHttpRequestWithBody;
import sonia.scm.net.ahc.AdvancedHttpResponse;

import java.io.IOException;

public class EventsCloudoguRestApiService {

  private final static String URL = "http://127.0.0.1:5000/";

  private final AdvancedHttpClient httpClient;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public EventsCloudoguRestApiService(AdvancedHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  private AdvancedHttpRequestWithBody createPostRequest(JsonNode payload) {
    final AdvancedHttpRequestWithBody postRequest = this.httpClient.post(URL);
    postRequest.jsonContent(payload);
    return postRequest;
  }

  /*
  private ObjectNode wrapPush(Push push) {
    final ObjectNode wrapper = objectMapper.createObjectNode();
    wrapper.set("push", push.toJsonNode());
    return wrapper;
  }
  */


  public void sendPush(Event eventDto) throws Exception {
    // Construct Request
    AdvancedHttpRequestWithBody postRequest = createPostRequest(eventDto.toJsonNode());

    final AdvancedHttpResponse postPushResponse;
    try {
      postPushResponse = postRequest.request();
      if (!postPushResponse.isSuccessful()) {
        throw new Exception("REEEEEEEEE"); //TODO: build own exception class
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
