package com.jb.pushevent.pathcollect;

import lombok.SneakyThrows;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import sonia.scm.repository.Changeset;
import sonia.scm.repository.Person;
import sonia.scm.repository.Repository;
import sonia.scm.repository.api.RepositoryService;


import java.util.HashSet;
import java.util.Set;

class PathCollectorTest {

  @Mock
  RepositoryService repositoryService;
  @Mock
  Repository repository;
  @Mock
  PathCollectFactory pathCollectorFactory;
  Set<Changeset> changesetSet;
  PathCollector pathCollector;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
    repositoryService = Mockito.mock(RepositoryService.class);
    repository = Mockito.mock(Repository.class);

    pathCollectorFactory = Mockito.mock(PathCollectFactory.class);
    Mockito.when(pathCollectorFactory.create(repository)).thenReturn(new PathCollector(repositoryService));

    changesetSet = new HashSet<>();

    Person p1 = new Person();
    Changeset c1 = new Changeset("id1", 20L, p1, "description for first changeset");
    changesetSet.add(c1);

    Person p12 = new Person();
    Changeset c2 = new Changeset("id2", 30L, p1, "description for second changeset");
    changesetSet.add(c2);

    Person p3 = new Person();
    Changeset c3 = new Changeset("id3", 40L, p1, "description for third changeset");
    changesetSet.add(c3);
  }

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    // pathCollector = pathCollectorFactory.create(repository);
  }


  @SneakyThrows
  @Test
  void collectAll() {
    pathCollector.collectAll(changesetSet);
  }

  @SneakyThrows
  @org.junit.jupiter.api.Test
  void collectSingle() {
    Changeset c = changesetSet.iterator().next();
    pathCollector.collectSingle(c);
  }

  @org.junit.jupiter.api.Test
  void close() {
    pathCollector.close();
  }
}
