package com.jb.pushevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sonia.scm.net.ahc.AdvancedHttpClient;
import sonia.scm.net.ahc.AdvancedHttpResponse;
import sonia.scm.net.ahc.BaseHttpRequest;
import sonia.scm.net.ahc.ContentTransformer;

import java.io.IOException;

public class HttpHandler extends AdvancedHttpClient {

  final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

  @Override
  protected ContentTransformer createTransformer(Class<?> type, String contentType) {
    return null;
  }

  @Override
  protected AdvancedHttpResponse request(BaseHttpRequest<?> request) throws IOException {

    AdvancedHttpClient client = new HttpHandler();

    AdvancedHttpResponse response = client.post("localhost.org")
      .formContent()
      .field("firstname", "Tricia")
      .field("lastname", "McMillan")
      .build()
      .request();

    if (response.isSuccessful()){
      System.out.println("success");
    }

    return response;
  }
}
