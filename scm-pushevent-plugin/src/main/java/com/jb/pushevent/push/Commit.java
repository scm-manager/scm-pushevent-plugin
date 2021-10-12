package com.jb.pushevent.push;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Commit implements Serializable {
  private String commitId;
  private String commitMessage;
  private Long dateCommitted;
  private Set<String> filesChanged = new HashSet<>();
}
