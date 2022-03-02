package com.jb.pushevent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jb.pushevent.config.PushEventConfigurationStore;
import com.jb.pushevent.dto.FileChanges;
import com.jb.pushevent.dto.Push;
import com.jb.pushevent.pathcollect.PathCollectFactory;
import com.jb.pushevent.pathcollect.PathCollector;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sonia.scm.net.ahc.AdvancedHttpClient;
import sonia.scm.repository.Changeset;
import sonia.scm.repository.Person;
import sonia.scm.repository.Repository;
import sonia.scm.repository.RepositoryHookEvent;
import sonia.scm.repository.api.HookChangesetBuilder;
import sonia.scm.repository.api.HookContext;
import sonia.scm.repository.api.HookFeature;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PushEventSubscriberTest {


  @Mock
  HookContext mockContext;

  @Mock
  RepositoryHookEvent mockRepositoryHookEvent;

  @Mock
  PathCollectFactory mockPathCollectorFactory;

  @Mock
  PathCollector mockPathCollector;

  @Mock
  Provider<AdvancedHttpClient> mockHttpClientProvider;

  @Mock
  Repository mockRepository;

  @Mock
  PushEventConfigurationStore mockPushEventConfigurationStore;

  @Mock
  HookChangesetBuilder mockHookChangesetBuilder;

  @Mock
  Subject subject;


  private Set<Changeset> createTestChangesets(){
    Set<Changeset> changesets = new HashSet<>();

    Person p1 = new Person("Jeff Bezos", "jeff.bezos@mail.com");
    Changeset c1 = new Changeset("id1", 20L, p1, "description for first changeset");
    changesets.add(c1);

    Person p2 = new Person("Elon Musk", "elon.musk@mail.com");
    Changeset c2 = new Changeset("id2", 30L, p2, "description for second changeset");
    changesets.add(c2);

    Person p3 = new Person("Bill Gates", "bill.gates@mail.com");
    Changeset c3 = new Changeset("id3", 40L, p3, "description for third changeset");
    changesets.add(c3);

    return changesets;
  }

  @Inject
  @Test
  public void createPushObjectFromEvent() throws IOException {

    Set<Changeset> changesets = createTestChangesets();

    when(mockRepositoryHookEvent.getContext()).thenReturn(mockContext);
    when(mockContext.isFeatureSupported(HookFeature.CHANGESET_PROVIDER)).thenReturn(true);
    when(mockContext.isFeatureSupported(HookFeature.CHANGESET_PROVIDER)).thenReturn(true);
    //when(mockContext.getChangesetProvider()).thenReturn(mockHookChangesetBuilder);
    //when(mockHookChangesetBuilder.getChangesets()).thenReturn(changesets);

    when(mockPathCollectorFactory.create(mockRepository)).thenReturn(mockPathCollector);
    when(mockPathCollector.collectAll(any())).thenReturn(new FileChanges(new ObjectMapper().createObjectNode()));

    PushEventSubscriber pushEventSubscriber = new PushEventSubscriber(mockPathCollectorFactory, mockHttpClientProvider, mockPushEventConfigurationStore);

    when(subject.hasRole(any())).thenReturn(true);
    when(subject.getPrincipal()).thenReturn("Bill Gates <bill.gates@mail.com>");

    try {
      Push push = pushEventSubscriber.createPushDtoFromEvent(mockRepository, changesets, mockRepositoryHookEvent, subject);
      assertNotNull(push);
      assertEquals("Bill Gates <bill.gates@mail.com>", push.getUser());
      assertEquals(3, push.getCommits().size());
    } catch (IOException e) {
      e.printStackTrace();
      fail("should not throw an exception here");
    }
  }
}
