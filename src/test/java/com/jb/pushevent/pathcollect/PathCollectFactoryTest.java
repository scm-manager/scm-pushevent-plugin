package com.jb.pushevent.pathcollect;

import sonia.scm.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sonia.scm.repository.api.RepositoryServiceFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PathCollectFactoryTest {

  PathCollectFactory pathCollectFactory;
  Repository repository;

  @BeforeEach
  void setUp() {
    RepositoryServiceFactory repositoryServiceFactory = mock(RepositoryServiceFactory.class);
    repository = mock(Repository.class);
    pathCollectFactory = new PathCollectFactory(repositoryServiceFactory);
  }

  @Test
  void create() {
    PathCollector pathCollector = pathCollectFactory.create(repository);
    assertNotNull(pathCollector);
  }
}
