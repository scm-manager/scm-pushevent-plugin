package com.jb.pushevent.config;

import com.google.inject.Inject;
import de.otto.edison.hal.Links;
import org.mapstruct.*;
import sonia.scm.api.v2.resources.LinkBuilder;
import sonia.scm.api.v2.resources.ScmPathInfoStore;

import static de.otto.edison.hal.Link.link;
import static de.otto.edison.hal.Links.linkingTo;

/**
 * packt links in das API links attribute
 */
@Mapper
public abstract class PushEventMapper {

  @Inject
  private ScmPathInfoStore scmPathInfoStore;

  public abstract PushEventConfiguration map(PushEventConfigurationDto dto);

  @Mapping(target = "attributes", ignore = true)
  public abstract PushEventConfigurationDto map(PushEventConfiguration config);


  @AfterMapping
  void appendLinks(@MappingTarget PushEventConfigurationDto target) {
    Links.Builder linksBuilder = linkingTo().self(self());
    linksBuilder.single(link("update", update()));
    target.add(linksBuilder.build());
  }

  private String self() {
    LinkBuilder linkBuilder = new LinkBuilder(scmPathInfoStore.get(), PushEventConfigurationResource.class);
    return linkBuilder.method("get").parameters().href();
  }

  private String update() {
    LinkBuilder linkBuilder = new LinkBuilder(scmPathInfoStore.get(), PushEventConfigurationResource.class);
    return linkBuilder.method("update").parameters().href();
  }

  void setScmPathInfoStore(ScmPathInfoStore scmPathInfoStore) {
    this.scmPathInfoStore = scmPathInfoStore;
  }

}
