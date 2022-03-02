package com.jb.pushevent.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushEventConfigurationTest {

  PushEventConfiguration pushEventConfiguration;

  @BeforeEach
  void setUp() {
    pushEventConfiguration = new PushEventConfiguration("url", "token", false);
  }

  @Test
  void getUrl() {
    assertEquals("url", pushEventConfiguration.getUrl());
  }

  @Test
  void getToken() {
    assertEquals("token", pushEventConfiguration.getToken());
  }

  @Test
  void getActive() {
    assertFalse(pushEventConfiguration.getActive());
  }

  @Test
  void isValid() {
    assertTrue(pushEventConfiguration.isValid());
  }

  @Test
  void setUrl() {
    pushEventConfiguration.setUrl("newurl");
    assertEquals("newurl", pushEventConfiguration.getUrl());
  }

  @Test
  void setToken() {
    pushEventConfiguration.setToken("newtoken");
    assertEquals("newtoken", pushEventConfiguration.getToken());
  }

  @Test
  void setActive() {
    pushEventConfiguration.setActive(true);
    assertTrue(pushEventConfiguration.getActive());
  }
}
