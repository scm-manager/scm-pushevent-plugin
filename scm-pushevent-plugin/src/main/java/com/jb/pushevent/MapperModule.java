package com.jb.pushevent;

import com.google.inject.AbstractModule;
import com.jb.pushevent.config.PushEventMapper;
import org.mapstruct.factory.Mappers;
import sonia.scm.plugin.Extension;

@Extension
public class MapperModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PushEventMapper.class).to(Mappers.getMapperClass(PushEventMapper.class));
  }
}
