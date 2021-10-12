package com.jb.pushevent.pathcollect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sonia.scm.repository.Changeset;
import sonia.scm.repository.Modifications;
import sonia.scm.repository.api.RepositoryService;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is a duplication of the implementation in scm-pathwp-plugin/src/main/java/sonia/scm/pathwp/PathCollector.java v2.0.2
 * normally the dependency to the scm-pathwp-plugin in build.gradle would solve this but the class is package private and as such not usable in
 * other plugin projects
 * <p>
 * Remove this class if the implementation in https://github.com/scm-manager/scm-pathwp-plugin/blob/5f21ff1b27814662155acd8933f331b20e6421d4/src/main/java/sonia/scm/pathwp/PathCollector.java#L37
 * is publicly available
 */
public class PathCollector implements Closeable {

  private static final Logger LOG = LoggerFactory.getLogger(PathCollector.class);

  private final RepositoryService repositoryService;
  private final Set<String> paths = new HashSet<>();

  PathCollector(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  public Set<String> collectAll(Iterable<Changeset> changesets) throws IOException {
    paths.clear();
    for (Changeset c : changesets) {
      collect(c);
    }
    return paths;
  }

  public Set<String> collectSingle(Changeset changeset) throws IOException {
    paths.clear();
    collect(changeset);
    return paths;
  }

  private void collect(Changeset changeset) throws IOException {
    Modifications modifications = repositoryService.getModificationsCommand()
      .revision(changeset.getId())
      .getModifications();

    if (modifications != null) {
      collect(modifications);
    } else {
      LOG.warn("there is no modifications for the changeset {}", changeset.getId());
    }
  }

  private void collect(Modifications modifications) {
    append(modifications.getEffectedPaths());
  }

  private void append(Iterable<String> modifiedPaths) {
    for (String path : modifiedPaths) {
      paths.add(normalizePath(path));
    }
  }

  private String normalizePath(String path) {
    if (path.startsWith("/")) {
      return path.substring(1);
    }
    return path;
  }

  @Override
  public void close() {
    repositoryService.close();
  }
}
