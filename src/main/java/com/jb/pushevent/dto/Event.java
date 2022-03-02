package com.jb.pushevent.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jb.pushevent.dto.Push;
import lombok.Getter;


@Getter
public class Event {

  private final ObjectNode node;

  String id;
  final String application = "scmm";
  final String type = "push";
  String time;

  ObjectNode data;

  public Event(ObjectNode node) {
    this.node = node;
    this.node.put("application", this.application);
    this.node.put("type", this.type);
  }

  public void setId(String id) {
    this.id = id;
    this.node.put("id", id);
  }

  public void setTime(String time) {
    this.time = time;
    this.node.put("time", time);
  }

  public void setData(Push data) {
    this.data = data.getNode();
    node.set("data", data.toJsonNode());
  }

  public JsonNode toJsonNode() {
    return node;
  }
}

/*
Beispiel eines Events das vom Redmine-Plugin an events.cloudogu.com gesendet wird

{
  "id": "d3106cff-701e-4625-9548-3f6cec67eb68",
  "application": "redmine",
  "type": "issue_created",
  "time": "2020-04-18T09:34:38.626+00:00",
  "data": {
    "id": 2,
    "subject": "Awesome Feature",
    "description": "New awesome feature",
    "lock_version": 0,
    "created_on": "2020-04-18T09:34:38.610Z",
    "updated_on": "2020-04-18T09:34:38.610Z",
      ...
    }
  }
}
 */
