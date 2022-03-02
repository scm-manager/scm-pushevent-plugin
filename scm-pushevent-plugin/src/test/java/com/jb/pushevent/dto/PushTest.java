package com.jb.pushevent.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PushTest {

  Push push;

  @BeforeEach
  void setUp() {
    push = new Push(new ObjectMapper().createObjectNode());
  }

  @Test
  void setId() {
    String expected = "push123";
    push.setId(expected);
    assertEquals(expected, push.getId());
  }

  @Test
  void getId() {
    String expected = "push123";
    push.setId(expected);
    assertEquals(expected, push.getId());
  }

  @Test
  void setUser() {
    String expected = "User Mc Userface";
    push.setUser(expected);
    assertEquals(expected, push.getUser());
  }

  @Test
  void getUser() {
    String expected = "User Mc Userface";
    push.setUser(expected);
    assertEquals(expected, push.getUser());
  }

  @Test
  void setDatePushed() {
    Long expected = 1499070300L;
    push.setDatePushed(expected);
    assertEquals(expected, push.getDatePushed());
  }

  @Test
  void getDatePushed() {
    Long expected = 1499070300L;
    push.setDatePushed(expected);
    assertEquals(expected, push.getDatePushed());
  }


  @Test
  void setRepositoryId() {
    String expected = "saf912DJ1230sadjk12p3";
    push.setRepositoryId(expected);
    assertEquals(expected, push.getRepositoryId());
  }

  @Test
  void getRepositoryId() {
    String expected = "saf912DJ1230sadjk12p3";
    push.setRepositoryId(expected);
    assertEquals(expected, push.getRepositoryId());
  }


  @Test
  void setRepositoryName() {
    String expected = "RepositoryName";
    push.setRepositoryName(expected);
    assertEquals(expected, push.getRepositoryName());
  }

  @Test
  void getRepositoryName() {
    String expected = "RepositoryName";
    push.setRepositoryName(expected);
    assertEquals(expected, push.getRepositoryName());
  }


  @Test
  void setRepositoryNamespace() {
    String expected = "RepositoryNamespace";
    push.setRepositoryNamespace(expected);
    assertEquals(expected, push.getRepositoryNamespace());
  }


  @Test
  void getRepositoryNamespace() {
    String expected = "RepositoryNamespace";
    push.setRepositoryNamespace(expected);
    assertEquals(expected, push.getRepositoryNamespace());
  }

  @Test
  void addCommit() {
    Commit commit = new Commit(new ObjectMapper().createObjectNode());
    push.addCommit(commit);
    assertFalse(push.getCommits().isEmpty());
  }

  @Test
  void setCommits() {
    Set<Commit> commits = Stream.of(new Commit(new ObjectMapper().createObjectNode())).collect(Collectors.toCollection(HashSet::new));
    push.setCommits(commits);
    assertFalse(push.getCommits().isEmpty());
  }

  @Test
  void getCommits() {
    Set<Commit> commits = Stream.of(new Commit(new ObjectMapper().createObjectNode())).collect(Collectors.toCollection(HashSet::new));
    push.setCommits(commits);
    assertFalse(push.getCommits().isEmpty());
  }

  @Test
  void toJsonNode() {
    assertNotNull(push.toJsonNode());
  }

  @Test
  void getNode() {
    assertNotNull(push.getNode());
  }
}
