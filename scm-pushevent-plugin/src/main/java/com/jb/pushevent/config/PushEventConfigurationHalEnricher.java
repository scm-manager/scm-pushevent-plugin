package com.jb.pushevent.config;

import sonia.scm.api.v2.resources.*;
import sonia.scm.plugin.Extension;

import javax.inject.Inject;
import javax.inject.Provider;

@Extension
@Enrich(Index.class)
public class PushEventConfigurationHalEnricher implements HalEnricher {

  private final Provider<ScmPathInfoStore> scmPathInfoStore;

  @Inject
  public PushEventConfigurationHalEnricher(Provider<ScmPathInfoStore> scmPathInfoStore) {
    this.scmPathInfoStore = scmPathInfoStore;
  }

  private String createLink() {
    return new LinkBuilder(scmPathInfoStore.get().get(), PushEventConfigurationResource.class)
      .method("get")
      .parameters()
      .href();
  }

  @Override
  public void enrich(HalEnricherContext context, HalAppender appender) {
    appender.appendLink("pushevent", createLink());
  }
}
