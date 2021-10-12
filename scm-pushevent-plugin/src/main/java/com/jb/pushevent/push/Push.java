package com.jb.pushevent.push;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Push implements Serializable {

  private String id;
  private String author;

  private Long datePushed;
  private String lastCommitMessage;

  private String repository;
  private Set<Commit> commits;

  private Set<String> filesChangedOverall = new HashSet<>();

  public void addCommit(Commit c) {
    commits.add(c);
  }
}


