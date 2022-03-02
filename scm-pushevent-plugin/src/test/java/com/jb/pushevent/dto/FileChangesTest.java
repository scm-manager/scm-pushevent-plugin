package com.jb.pushevent.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileChangesTest {

  FileChanges fileChanges;

  @BeforeEach
  void setUp() {
    fileChanges = new FileChanges(new ObjectMapper().createObjectNode());
  }

  @Test
  void setAdded() {
    Set<String> added = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setAdded(added);
    assertNotNull(fileChanges.getAdded());
  }

  @Test
  void getAdded() {
    Set<String> added = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setAdded(added);
    assertEquals(added, fileChanges.getAdded());
  }

  @Test
  void setModified() {
    Set<String> modified = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setModified(modified);
    assertNotNull(fileChanges.getModified());
  }

  @Test
  void getModified() {
    Set<String> modified = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setModified(modified);
    assertEquals(modified, fileChanges.getModified());
  }

  @Test
  void setMoved() {
    Set<String> moved = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setMoved(moved);
    assertNotNull(fileChanges.getMoved());
  }

  @Test
  void getMoved() {
    Set<String> moved = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setMoved(moved);
    assertEquals(moved, fileChanges.getMoved());
  }

  @Test
  void setCopied() {
    Set<String> copied = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setCopied(copied);
    assertNotNull(fileChanges.getCopied());
  }

  @Test
  void getCopied() {
    Set<String> copied = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setCopied(copied);
    assertEquals(copied, fileChanges.getCopied());
  }

  @Test
  void setRemoved() {
    Set<String> removed = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setRemoved(removed);
    assertNotNull(fileChanges.getRemoved());
  }

  @Test
  void getRemoved() {
    Set<String> removed = Stream.of("file").collect(Collectors.toCollection(HashSet::new));
    fileChanges.setRemoved(removed);
    assertEquals(removed, fileChanges.getRemoved());
  }

  @Test
  void toJsonNode() {
    assertNotNull(fileChanges.toJsonNode());
  }

  @Test
  void getNode() {
    assertNotNull(fileChanges.getNode());
  }

}
