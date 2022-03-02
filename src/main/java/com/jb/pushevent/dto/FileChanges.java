package com.jb.pushevent.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;


import java.util.HashSet;
import java.util.Set;

@Getter
public class FileChanges {

  private final ObjectNode node;

  public FileChanges(ObjectNode node) {
    this.node = node;
  }

  private Set<String> added = new HashSet<>();
  private Set<String> modified = new HashSet<>();
  private Set<String> removed = new HashSet<>();
  private Set<String> copied = new HashSet<>();
  private Set<String> moved = new HashSet<>();


  public void setAdded(Set<String> added) {
    this.added = added;
    ArrayNode arrayNode = this.node.putArray("added");
    added.forEach(arrayNode::add);
  }

  public void setModified(Set<String> modified) {
    this.modified = modified;
    ArrayNode arrayNode = this.node.putArray("modified");
    modified.forEach(arrayNode::add);
  }

  public void setRemoved(Set<String> removed) {
    this.removed = removed;
    ArrayNode arrayNode = this.node.putArray("removed");
    removed.forEach(arrayNode::add);
  }

  public void setMoved(Set<String> moved) {
    this.moved = moved;
    ArrayNode arrayNode = this.node.putArray("moved");
    moved.forEach(arrayNode::add);
  }

  public void setCopied(Set<String> copied) {
    this.copied = copied;
    ArrayNode arrayNode = this.node.putArray("copied");
    copied.forEach(arrayNode::add);
  }


  public JsonNode toJsonNode() {
    return node;
  }

}
