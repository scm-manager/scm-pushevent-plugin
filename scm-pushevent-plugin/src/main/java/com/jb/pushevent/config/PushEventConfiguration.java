package com.jb.pushevent.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sonia.scm.Validateable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pushevent-configuration")
public class PushEventConfiguration implements Validateable {

  /**
   * endpoint server url
   */
  private String url;
  private String token;
  private boolean active = false;

  public String getUrl() {
    return this.url;
  }

  public String getToken() {
    return this.token;
  }

  public boolean getActive() {
    return this.active;
  }

  @Override
  public boolean isValid() {
    return !url.isEmpty() && !token.isEmpty();
  }

  public PushEventConfiguration(String url, String token, Boolean active) {
    this.url = url;
    this.token = token;
    this.active = active;
  }

}
