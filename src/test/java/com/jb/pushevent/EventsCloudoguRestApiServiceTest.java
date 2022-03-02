package com.jb.pushevent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jb.pushevent.config.PushEventConfiguration;
import com.jb.pushevent.config.PushEventConfigurationStore;
import com.jb.pushevent.dto.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import sonia.scm.net.ahc.AdvancedHttpClient;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventsCloudoguRestApiServiceTest {

  EventsCloudoguRestApiService eventsCloudoguRestApiService;

  @Mock
  AdvancedHttpClient httpClient;

  PushEventConfigurationStore pushEventConfigurationStore;

  @BeforeEach
  void setUp() {
    pushEventConfigurationStore = mock(PushEventConfigurationStore.class);
    PushEventConfiguration pushEventConfiguration = mock(PushEventConfiguration.class);

    when(pushEventConfiguration.getUrl()).thenReturn("/endpoint");
    when(pushEventConfiguration.getToken()).thenReturn("token");
    when(pushEventConfiguration.getActive()).thenReturn(true);
    when(pushEventConfigurationStore.get()).thenReturn(pushEventConfiguration);

    eventsCloudoguRestApiService = new EventsCloudoguRestApiService(httpClient, pushEventConfigurationStore);
  }

  @Test
  void sendPush() {
    Event event = new Event(new ObjectMapper().createObjectNode());
      assertThrows(RuntimeException.class, () -> {
        eventsCloudoguRestApiService.sendPush(event);
      });
  }
}
