package com.jb.pushevent.push;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;


@Getter
public class Push {

  private final ObjectNode node;

  private String id;
  private String author;

  private String instanceId;

  private Long datePushed;

  private String repositoryId;
  private String repositoryName;

  private Set<Commit> commits;
  private Commit lastCommit;

  private Set<String> filesChangedOverall = new HashSet<>();

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

  public void setLastCommit(Commit lastCommit) {
    this.lastCommit = lastCommit;
    node.set("pushCommit", this.lastCommit.toJsonNode());
  }

  public void setRepositoryId(String repositoryId) {
    this.repositoryId = repositoryId;
    node.put("repositoryId", this.repositoryId);
  }

  public void setRepositoryName(String repositoryName) {
    this.repositoryName = repositoryName;
    node.put("repositoryName", this.repositoryName);
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

  public void setFilesChangedOverall(Set<String> filesChangedOverall) {
    this.filesChangedOverall = filesChangedOverall;
    ArrayNode arrayNode = node.putArray("filesChangedOverall");
    filesChangedOverall.forEach(arrayNode::add);
  }

  public JsonNode toJsonNode() {
    return node;
  }
}


