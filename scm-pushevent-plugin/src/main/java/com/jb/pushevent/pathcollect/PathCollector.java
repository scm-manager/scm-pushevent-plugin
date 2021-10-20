package com.jb.pushevent.pathcollect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jb.pushevent.dto.FileChanges;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sonia.scm.repository.Changeset;
import sonia.scm.repository.Modifications;
import sonia.scm.repository.api.RepositoryService;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;

/**
 * The PathCollector class collects all types of modifications which are part of a changeset.
 *
 * These modification types are additions, modifications, coping of files, moving of files and file removables.
 */
public class PathCollector implements Closeable {

  private static final Logger LOG = LoggerFactory.getLogger(PathCollector.class);

  private final RepositoryService repositoryService;

  private final Set<String> added = new HashSet<>();
  private final Set<String> removed = new HashSet<>();
  private final Set<String> modified = new HashSet<>();
  private final Set<String> copied = new HashSet<>();
  private final Set<String> moved = new HashSet<>();

  FileChanges fileChanges = new FileChanges(new ObjectMapper().createObjectNode());


  PathCollector(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  public FileChanges collectAll(Iterable<Changeset> changesets) throws IOException {
    added.clear();
    removed.clear();
    modified.clear();
    copied.clear();
    moved.clear();
    for (Changeset c : changesets) {
      collect(c);
    }
    return fileChanges;
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
    modifications.getAdded().forEach((add) -> {
      appendNormalizedPathToSet(added, add.toString());
    });
    modifications.getRemoved().forEach((rmv) -> {
      appendNormalizedPathToSet(removed, rmv.toString());
    });
    modifications.getModified().forEach((mod) -> {
      appendNormalizedPathToSet(modified, mod.toString());
    });
    modifications.getRenamed().forEach((mov) -> {
      appendNormalizedPathToSet(moved, mov.toString());
    });
    modifications.getAdded().forEach((cpy) -> {
      appendNormalizedPathToSet(copied, cpy.toString());
    });

    fileChanges.setAdded(added);
    fileChanges.setRemoved(removed);
    fileChanges.setModified(modified);
    fileChanges.setMoved(moved);
    fileChanges.setCopied(copied);
  }

  private void appendNormalizedPathToSet(Set<String> modificationSet, String modifiedPaths) {
    modificationSet.add(normalizePath(modifiedPaths));
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
