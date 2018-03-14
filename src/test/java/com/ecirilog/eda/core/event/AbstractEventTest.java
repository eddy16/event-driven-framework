package com.ecirilog.eda.core.event;

import org.junit.Assert;
import org.junit.Test;


public class AbstractEventTest {
    @Test
    public final void getTypeTest() {
        final AbstractEvent abstractIntance = new AbstractEventChild();
        Assert.assertEquals(AbstractEventChild.class,abstractIntance.getType());
    }

    public static class AbstractEventChild extends AbstractEvent {
    }
}