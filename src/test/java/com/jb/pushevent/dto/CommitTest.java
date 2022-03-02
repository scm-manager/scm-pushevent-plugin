package com.jb.pushevent.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CommitTest {

  Commit commit;

  @BeforeEach
  void setUp() {
    commit = new Commit(new ObjectMapper().createObjectNode());
  }

  @Test
  void setCommitId() {
    String expected = "123commitId";
    assertNull(commit.getCommitId());
    commit.setCommitId(expected);
    assertNotNull(commit.getCommitId());
    assertEquals(expected, commit.getCommitId());
  }

  @Test
  void setCommitMessage() {
    String expected = "My Message of a regular commit";
    assertNull(commit.getCommitMessage());
    commit.setCommitMessage(expected);
    assertNotNull(commit.getCommitMessage());
    assertEquals(expected, commit.getCommitMessage());
  }

  @Test
  void setDateCommitted() {
    Long expected = 1646160400L;
    assertNull(commit.getDateCommitted());
    commit.setDateCommitted(expected);
    assertNotNull(commit.getDateCommitted());
    assertEquals(expected, commit.getDateCommitted());
  }

  @Test
  void setAuthor() {
    String expected = "Author Mc Authorface";
    assertNull(commit.getAuthor());
    commit.setAuthor(expected);
    assertNotNull(commit.getAuthor());
    assertEquals(expected, commit.getAuthor());
  }

  @Test
  void getCommitId() {
    String expected = "fe124Cs321DS123DSAD1312lolE6867gfdg";
    assertNull(commit.getCommitId());
    commit.setCommitId(expected);
    assertNotNull(commit.getCommitId());
    assertEquals(expected, commit.getCommitId());
  }

  @Test
  void getCommitMessage() {
    String expected = "My Message of a regular commit";
    commit.setCommitMessage(expected);
    assertNotNull(commit.getCommitMessage());
    assertEquals(expected, commit.getCommitMessage());
  }

  @Test
  void getDateCommitted() {
    Long expected = 1646160400L;
    commit.setDateCommitted(expected);
    assertNotNull(commit.getDateCommitted());
    assertEquals(expected, commit.getDateCommitted());
  }

  @Test
  void getAuthor() {
    String expected = "Author Mc Authorface";
    commit.setAuthor(expected);
    assertNotNull(commit.getAuthor());
    assertEquals(expected, commit.getAuthor());
  }

  @Test
  void setFilesChanged() {
    Set<String> fileChangeAdded = Stream.of("a", "b", "c").collect(Collectors.toCollection(HashSet::new));
    FileChanges fileChanges = new FileChanges(new ObjectMapper().createObjectNode());
    fileChanges.setAdded(fileChangeAdded);
    commit.setFilesChanged(fileChanges);
    assertNotNull(commit.getFileChanges());
    assertEquals(fileChanges, commit.getFileChanges());
  }

  @Test
  void getFileChanges() {
    Set<String> fileChangeAdded = Stream.of("a", "b", "c").collect(Collectors.toCollection(HashSet::new));
    FileChanges fileChanges = new FileChanges(new ObjectMapper().createObjectNode());
    fileChanges.setAdded(fileChangeAdded);
    commit.setFilesChanged(fileChanges);
    assertNotNull(commit.getFileChanges());
    assertEquals(fileChanges, commit.getFileChanges());
  }

  @Test
  void setBranches() {
    List<String> branches = Stream.of("a", "b", "c").collect(Collectors.toCollection(ArrayList::new));
    commit.setBranches(branches);
    assertEquals(branches, commit.getBranches());
  }

  @Test
  void getBranches() {
    List<String> branches = Stream.of("a", "b", "c").collect(Collectors.toCollection(ArrayList::new));
    commit.setBranches(branches);
    assertEquals(branches, commit.getBranches());
  }

  @Test
  void toJsonNode() {
    assertNotNull(commit.toJsonNode());
  }

  @Test
  void getNode() {
    assertNotNull(commit.getNode());
  }
}
