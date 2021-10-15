package com.jb.pushevent.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Commit {

  private final ObjectNode node;

  private String commitId;
  private String commitMessage;
  private Long dateCommitted;
  private String author;
  private Set<String> filesChanged = new HashSet<>();

  public Commit(ObjectNode node) {
    this.node = node;
  }

  public void setCommitId(String id) {
    this.commitId = id;
    node.put("commitId", commitId);
  }

  public void setCommitMessage(String commitMessage) {
    this.commitMessage = commitMessage;
    node.put("message", commitMessage);
  }

  public void setDateCommitted(Long dateCommitted) {
    this.dateCommitted = dateCommitted;
    node.put("dateCommitted", dateCommitted);
  }

  public void setFilesChanged(Set<String> filesChanged) {
    this.filesChanged = filesChanged;
    ArrayNode arrayNode = node.putArray("filesChanged");
    filesChanged.forEach(arrayNode::add);
  }

  public void setAuthor(String author) {
    this.author = author;
    node.put("author", author);
  }

  public JsonNode toJsonNode() {
    return node;
  }
}
