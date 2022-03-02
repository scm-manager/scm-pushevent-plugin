package com.jb.pushevent.config;

import de.otto.edison.hal.HalRepresentation;
import de.otto.edison.hal.Links;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("java:S2160") // we do not need equals and hashcode in dto
public class PushEventConfigurationDto extends HalRepresentation {
  private String url;
  private String token;
  private Boolean active;

  @Override
  @SuppressWarnings("squid:S1185") // We want to have this method available in this package
  protected HalRepresentation add(Links links) {
    return super.add(links);
  }
}
