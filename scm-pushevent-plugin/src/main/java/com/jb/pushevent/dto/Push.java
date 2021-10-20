package com.jb.pushevent.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;


@Getter
public class Push {

  private final ObjectNode node;

  // todo NAMESPACE + Name ist eindeutig Name allein nicht!

  private String id;
  private String author;

  private String instanceId;

  private Long datePushed; // client timestamp

  private String repositoryId;
  private String repositoryNamespace;
  private String repositoryName;

  private Set<Commit> commits;

  // private Commit lastCommit;

  // private Set<String> filesChangedOverall = new HashSet<>();

  public Push(ObjectNode node) {
    this.node = node;
  }

  public void setId(String id) {
    this.id = id;
    node.put("id", this.id);
  }

  public void setInstanceId(String id) {
    this.id = id;
    node.put("id", this.id);
  }

  public void setAuthor(String author) {
    this.author = author;
    node.put("author", this.author);
  }

  public void setDatePushed(Long datePushed) {
    this.datePushed = datePushed;
    node.put("datePushed", this.datePushed);
  }

  public void setRepositoryId(String repositoryId) {
    this.repositoryId = repositoryId;
    node.put("repositoryId", this.repositoryId);
  }

  public void setRepositoryName(String repositoryName) {
    this.repositoryName = repositoryName;
    node.put("repositoryName", this.repositoryName);
  }

  public void setRepositoryNamespace(String repositoryNamespace) {
    this.repositoryNamespace = repositoryNamespace;
    node.put("repositoryNamespace", this.repositoryNamespace);
  }

  public void addCommit(Commit c) {
    if (this.commits == null) {
      commits = new HashSet<>();
    }
    commits.add(c);
  }

  public void setCommits(Set<Commit> commits) {
    this.commits = commits;
    ArrayNode arrayNode = node.putArray("commits");
    commits.forEach(commit -> {
      arrayNode.add(commit.toJsonNode());
    });
  }

  public JsonNode toJsonNode() {
    return node;
  }
}


