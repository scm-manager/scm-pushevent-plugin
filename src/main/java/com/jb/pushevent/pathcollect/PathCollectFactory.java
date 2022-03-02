package com.jb.pushevent.pathcollect;

import com.google.common.annotations.VisibleForTesting;
import sonia.scm.repository.Repository;
import sonia.scm.repository.api.RepositoryServiceFactory;

import javax.inject.Inject;

/**
 * This class is a duplication of the implementation in scm-pathwp-plugin/src/main/java/sonia/scm/pathwp/PathCollectorFactory.java v2.0.2
 * normally the dependency to the scm-pathwp-plugin in build.gradle would solve this but the class is package private and as such not usable in
 * other plugin projects
 * <p>
 * Remove this class if the implementation in https://github.com/scm-manager/scm-pathwp-plugin/blob/5f21ff1b27814662155acd8933f331b20e6421d4/src/main/java/sonia/scm/pathwp/PathCollectorFactory.java#L37
 * is publicly available
 */
public class PathCollectFactory {

  private final RepositoryServiceFactory repositoryServiceFactory;

  @Inject
  public PathCollectFactory(RepositoryServiceFactory repositoryServiceFactory) {
    this.repositoryServiceFactory = repositoryServiceFactory;
  }

  @VisibleForTesting
  public PathCollector create(Repository repository) {
    return new PathCollector(repositoryServiceFactory.create(repository));
  }
}
