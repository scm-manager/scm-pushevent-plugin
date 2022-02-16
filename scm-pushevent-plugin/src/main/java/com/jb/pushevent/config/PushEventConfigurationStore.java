package com.jb.pushevent.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import sonia.scm.config.ConfigurationPermissions;
import sonia.scm.store.ConfigurationStore;
import sonia.scm.store.ConfigurationStoreFactory;

import javax.validation.constraints.NotNull;

@Singleton
public class PushEventConfigurationStore {

  /**
   * configuration store name
   */
  private static final String NAME = "pushevent";

  //~--- fields ---------------------------------------------------------------
  /**
   * global configuration store
   */
  private final ConfigurationStore<PushEventConfiguration> store;
  private final ConfigurationStoreFactory storeFactory;

  @Inject
  public PushEventConfigurationStore(ConfigurationStoreFactory storeFactory) {
    this.storeFactory = storeFactory;
    this.store = storeFactory.withType(PushEventConfiguration.class).withName(NAME).build();
  }

  public void update(@NotNull PushEventConfiguration config) {
    createStore().set(config);
  }

  public PushEventConfiguration get() {
    PushEventConfiguration globalConfig = createStore().get();
    if (globalConfig == null) {
      globalConfig = new PushEventConfiguration();
    }
    return globalConfig;
  }

  private ConfigurationStore<PushEventConfiguration> createStore() {
    return storeFactory.withType(PushEventConfiguration.class).withName(NAME).build();
  }

  /*
    public PushEventConfiguration getGlobalConfiguration() {
    return store
      .getOptional()
      .orElse(new PushEventConfiguration());
  }

  public void setGlobalConfiguration(@Valid PushEventConfigurationDto updatedConfig, PushEventConfiguration configuration) {
    this.store.set(configuration);
  }
  */
}

