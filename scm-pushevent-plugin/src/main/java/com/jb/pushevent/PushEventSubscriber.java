package com.jb.pushevent;


import com.google.common.collect.Iterables;
import com.jb.pushevent.pathcollect.PathCollectFactory;
import com.jb.pushevent.pathcollect.PathCollector;
import com.jb.pushevent.push.Commit;
import com.jb.pushevent.push.Push;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sonia.scm.EagerSingleton;
import sonia.scm.plugin.Extension;
import com.github.legman.Subscribe;
import sonia.scm.repository.Changeset;
import sonia.scm.repository.PostReceiveRepositoryHookEvent;
import sonia.scm.repository.Repository;
import sonia.scm.repository.RepositoryHookEvent;
import sonia.scm.repository.api.HookContext;
import sonia.scm.repository.api.HookFeature;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Extension
@EagerSingleton
public class PushEventSubscriber {

  private final PathCollectFactory pathCollectorFactory;

  private static final Logger logger = LoggerFactory.getLogger(PushEventSubscriber.class);

  @Inject
  public PushEventSubscriber(PathCollectFactory pathCollectorFactory) {
    this.pathCollectorFactory = pathCollectorFactory;
  }

  @Subscribe
  public void onEvent(PostReceiveRepositoryHookEvent event) {
    logger.info("Event fired! Its a: " + event.getType());
    handlePushEvent(event);

    // event.getContext().getChangesetProvider().getChangesets().iterator().next().getDescription()
  }

  private void handlePushEvent(RepositoryHookEvent event) {
    Repository repository = event.getRepository();

    if (repository != null) {
      Iterable<Changeset> changesets = event.getContext().getChangesetProvider().getChangesets();
      logger.info("Ctx: " + event.getContext().toString());

      if (!Iterables.isEmpty(changesets)) {

        try {
          handlePush(repository, changesets, event);
        } catch (IOException e) {
          e.printStackTrace();
        }

      } else {
        logger.warn("received hook without changesets");
      }
    } else {
      logger.warn("received hook without repository");
    }
  }

  private void handlePush(Repository repository, Iterable<Changeset> changesets, RepositoryHookEvent event) throws IOException {
    logger.info("<<<<<<<<<<<handlePush>>>>>>>>>>>>");
    System.out.println("repo " + repository);

    Push push = new Push();
    push.setRepository(repository.getId());

    for (Changeset changeset : changesets) {
      Commit commit = new Commit();

      commit.setCommitId(changeset.getId());
      commit.setCommitMessage(changeset.getDescription());
      commit.setFilesChanged(collectPaths(event.getContext(), repository, changeset));
      commit.setDateCommitted(changeset.getCreationDate());

      push.addCommit(commit);

      System.out.println("description " + changeset.getDescription());
      System.out.println("type " + changeset.getType());
      System.out.println("author " + changeset.getAuthor());

      if (!changesets.iterator().hasNext()) {
        System.out.println("LAST ONE");
        push.setDatePushed(commit.getDateCommitted());
        push.setLastCommitMessage(commit.getCommitMessage());
      }
    }

    push.setFilesChangedOverall(collectAllPaths(event.getContext(), repository));

  }

  private Set<String> collectAllPaths(HookContext eventContext, Repository repository) throws IOException {
    if (eventContext.isFeatureSupported(HookFeature.CHANGESET_PROVIDER)) {
      try (PathCollector collector = pathCollectorFactory.create(repository)) {
        return collector.collectAll(eventContext.getChangesetProvider().getChangesets());
      }
    }

    return Collections.emptySet();
  }

  private Set<String> collectPaths(HookContext eventContext, Repository repository, Changeset changeset) throws IOException {
    if (eventContext.isFeatureSupported(HookFeature.CHANGESET_PROVIDER)) {
      try (PathCollector collector = pathCollectorFactory.create(repository)) {
        return collector.collectSingle(changeset);
      }
    }

    return Collections.emptySet();
  }

}
