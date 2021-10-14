package com.jb.pushevent;

import com.jb.pushevent.pathcollect.PathCollectFactory;
import com.jb.pushevent.pathcollect.PathCollector;
import com.jb.pushevent.push.Push;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import sonia.scm.net.ahc.AdvancedHttpClient;
import sonia.scm.repository.*;
import sonia.scm.repository.api.HookChangesetBuilder;
import sonia.scm.repository.api.HookContext;
import sonia.scm.repository.api.HookFeature;
import sonia.scm.repository.spi.HookChangesetProvider;

import javax.inject.Inject;
import javax.inject.Provider;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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
  HookChangesetBuilder mockHookChangesetBuilder;

  @Inject
  @Test
  public void createPushObjectFromEvent() throws IOException {

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


    when(mockRepositoryHookEvent.getContext()).thenReturn(mockContext);
    when(mockContext.isFeatureSupported(HookFeature.CHANGESET_PROVIDER)).thenReturn(true);
    when(mockContext.isFeatureSupported(HookFeature.CHANGESET_PROVIDER)).thenReturn(true);
    when(mockContext.getChangesetProvider()).thenReturn(mockHookChangesetBuilder);
    when(mockHookChangesetBuilder.getChangesets()).thenReturn(changesets);

    when(mockPathCollector.collectAll(changesets)).thenReturn(new HashSet<>(Arrays.asList("filea", "fileb", "filec")));
    when(mockPathCollector.collectSingle(c1)).thenReturn(new HashSet<>(Arrays.asList("filea")));
    when(mockPathCollector.collectSingle(c2)).thenReturn(new HashSet<>(Arrays.asList("filea", "fileb")));
    when(mockPathCollector.collectSingle(c3)).thenReturn(new HashSet<>(Arrays.asList("filea", "filec")));

    when(mockPathCollectorFactory.create(mockRepository)).thenReturn(mockPathCollector);

    PushEventSubscriber pushEventSubscriber = new PushEventSubscriber(mockPathCollectorFactory, mockHttpClientProvider);


    try {
      Push push = pushEventSubscriber.createPushObjectFromEvent(mockRepository, changesets, mockRepositoryHookEvent);
      assertNotNull(push);
      assertEquals("Bill Gates <bill.gates@mail.com>", push.getAuthor());
      assertEquals(3, push.getCommits().size());
      assertEquals("id3", push.getLastCommit().getCommitId());
      assertEquals(2, push.getFilesChangedOverall().size());
    } catch (IOException e) {
      e.printStackTrace();
      fail("should not throw an exception here");
    }


  }
}
