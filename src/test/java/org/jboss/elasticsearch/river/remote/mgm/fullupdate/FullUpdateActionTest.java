/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.jboss.elasticsearch.river.remote.mgm.fullupdate;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.internal.InternalClient;
import org.jboss.elasticsearch.river.remote.mgm.fullupdate.FullUpdateAction;
import org.jboss.elasticsearch.river.remote.mgm.fullupdate.FullUpdateRequestBuilder;
import org.jboss.elasticsearch.river.remote.mgm.fullupdate.FullUpdateResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit test for {@link FullUpdateAction}.
 * 
 * @author Vlastimil Elias (velias at redhat dot com)
 */
public class FullUpdateActionTest {

  @Test
  public void constructor() {
    Assert.assertEquals(FullUpdateAction.NAME, FullUpdateAction.INSTANCE.name());
  }

  @Test
  public void newRequestBuilder() {
    Client client = Mockito.mock(InternalClient.class);

    FullUpdateRequestBuilder rb = FullUpdateAction.INSTANCE.newRequestBuilder(client);
    Assert.assertNotNull(rb);
  }

  @Test
  public void newResponse() {
    FullUpdateResponse rb = FullUpdateAction.INSTANCE.newResponse();
    Assert.assertNotNull(rb);
  }
}
