/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.jboss.elasticsearch.river.remote.mgm.fullupdate;

import java.io.IOException;

import junit.framework.Assert;

import org.elasticsearch.common.io.stream.BytesStreamInput;
import org.elasticsearch.common.io.stream.BytesStreamOutput;
import org.jboss.elasticsearch.river.remote.mgm.fullupdate.FullUpdateRequest;
import org.junit.Test;

/**
 * Unit test for {@link FullUpdateRequest}.
 * 
 * @author Vlastimil Elias (velias at redhat dot com)
 */
public class FullUpdateRequestTest {

  @Test
  public void constructor_empty() {
    {
      FullUpdateRequest tested = new FullUpdateRequest();

      tested.setRiverName("myriver");
      tested.setSpaceKey("AAA");
      Assert.assertEquals("myriver", tested.getRiverName());
      Assert.assertEquals("AAA", tested.getSpaceKey());
    }
  }

  @Test
  public void constructor_filling() {

    try {
      new FullUpdateRequest(null, "AAA");
      Assert.fail("IllegalArgumentException must be thrown");
    } catch (IllegalArgumentException e) {
      // OK
    }
    {
      FullUpdateRequest tested = new FullUpdateRequest("myriver", null);
      Assert.assertEquals("myriver", tested.getRiverName());
      Assert.assertNull(tested.getSpaceKey());
      Assert.assertFalse(tested.isSpaceKeyRequest());
    }

    {
      FullUpdateRequest tested = new FullUpdateRequest("myriver", "AAA");
      Assert.assertEquals("myriver", tested.getRiverName());
      Assert.assertEquals("AAA", tested.getSpaceKey());
      Assert.assertTrue(tested.isSpaceKeyRequest());
    }
  }

  @Test
  public void serialization() throws IOException {

    {
      FullUpdateRequest testedSrc = new FullUpdateRequest();
      FullUpdateRequest testedTarget = performserialization(testedSrc);
      Assert.assertNull(testedTarget.getRiverName());
      Assert.assertNull(testedTarget.getSpaceKey());
    }

    {
      FullUpdateRequest testedSrc = new FullUpdateRequest("myriver", null);
      FullUpdateRequest testedTarget = performserialization(testedSrc);
      Assert.assertEquals("myriver", testedTarget.getRiverName());
      Assert.assertNull(testedTarget.getSpaceKey());
    }

    {
      FullUpdateRequest testedSrc = new FullUpdateRequest("myriver", "ORG");
      FullUpdateRequest testedTarget = performserialization(testedSrc);
      Assert.assertEquals("myriver", testedTarget.getRiverName());
      Assert.assertEquals("ORG", testedTarget.getSpaceKey());
    }

  }

  /**
   * @param testedSrc
   * @return
   * @throws IOException
   */
  private FullUpdateRequest performserialization(FullUpdateRequest testedSrc) throws IOException {
    BytesStreamOutput out = new BytesStreamOutput();
    testedSrc.writeTo(out);
    FullUpdateRequest testedTarget = new FullUpdateRequest();
    testedTarget.readFrom(new BytesStreamInput(out.bytes()));
    return testedTarget;
  }

}
