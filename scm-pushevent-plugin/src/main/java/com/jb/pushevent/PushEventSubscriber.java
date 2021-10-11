package com.jb.pushevent;


import com.google.common.collect.Iterables;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sonia.scm.EagerSingleton;
import sonia.scm.plugin.Extension;
import sonia.scm.HandlerEventType;
import com.github.legman.Subscribe;
import sonia.scm.repository.Changeset;
import sonia.scm.repository.PostReceiveRepositoryHookEvent;
import sonia.scm.repository.Repository;
import sonia.scm.repository.RepositoryHookEvent;

import javax.inject.Inject;
import javax.security.auth.Subject;

@Extension
@EagerSingleton
public class PushEventSubscriber {

  private static final Logger logger = LoggerFactory.getLogger(PushEventSubscriber.class);

  /*
  @Inject
  public PullRequestEventSubscriber() {
  }
  */

  @Subscribe
  public void onEvent(PostReceiveRepositoryHookEvent event) {
    handlePushEvent("", event);
  }

  private void handlePushEvent(String username, RepositoryHookEvent event) {
    Repository repository = event.getRepository();

    if (repository != null) {
      Iterable<Changeset> changesets = event.getContext().getChangesetProvider().getChangesets();
      logger.info("Ctx: "+ event.getContext().toString());

      if (!Iterables.isEmpty(changesets)) {
        handlePush(username, repository, changesets);
      } else {
        logger.warn("received hook without changesets");
      }
    } else {
      logger.warn("received hook without repository");
    }
  }

  private void handlePush(String username, Repository repository, Iterable<Changeset> changesets) {

  }
}
