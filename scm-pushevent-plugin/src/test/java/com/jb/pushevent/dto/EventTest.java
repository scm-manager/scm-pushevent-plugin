package com.jb.pushevent.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class EventTest {

  Event event;

  @BeforeEach
  void setUp() {
    event = new Event(new ObjectMapper().createObjectNode());
  }

  @Test
  void setId() {
    String expected = "123EventId";
    event.setId(expected);
    assertEquals(expected, event.getId());
  }

  @Test
  void getId() {
    String expected = "123EventId";
    event.setId(expected);
    assertEquals(expected, event.getId());
  }

  @Test
  void setTime() {
    String expected = "12-45-12";
    event.setTime(expected);
    assertEquals(expected, event.getTime());
  }

  @Test
  void getTime() {
    String expected = "12-45-12";
    event.setTime(expected);
    assertEquals(expected, event.getTime());
  }

  @Test
  void setData() {
    Push push = new Push(new ObjectMapper().createObjectNode());
    event.setData(push);
    assertNotNull(event.getData());
  }

  @Test
  void getData() {
    Push push = new Push(new ObjectMapper().createObjectNode());
    event.setData(push);
    assertNotNull(event.getData());
  }

  @Test
  void toJsonNode() {
    assertNotNull(event.toJsonNode());
  }

  @Test
  void getNode() {
    assertNotNull(event.getNode());
  }


  @Test
  void getApplication() {
    assertEquals("scmm", event.getApplication());
  }

  @Test
  void getType() {
    assertEquals("push", event.getType());
  }



}
