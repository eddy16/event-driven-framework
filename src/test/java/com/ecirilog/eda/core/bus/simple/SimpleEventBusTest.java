package com.ecirilog.eda.core.bus.simple;

import com.ecirilog.eda.core.bus.EventBus;
import com.ecirilog.eda.core.event.AbstractEventTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleEventBusTest {
    private static final Logger LOGGER = LogManager.getLogger(SimpleEventBusTest.class);

    private EventBus eventBus;

    @Before
    public void setUp() {
        eventBus = new SimpleEventBus();
    }

    @Test
    public void simpleEventBusConstructor() {
        Assert.assertNotNull(eventBus);
    }

    @Test
    public void post() {
        final AbstractEventTest.AbstractEventChild simpleEvent = new AbstractEventTest.AbstractEventChild();

        eventBus.register(AbstractEventTest.AbstractEventChild.class, event -> {
            LOGGER.debug("event: " + event.getType());
            Assert.assertEquals(AbstractEventTest.AbstractEventChild.class, event.getType());
            Assert.assertEquals(simpleEvent, event);
            AbstractEventTest.AbstractEventChild simple = (AbstractEventTest.AbstractEventChild) event;
            Assert.assertEquals(simpleEvent.getType(), simple.getType());
        });

        eventBus.post(simpleEvent);
        eventBus.post(simpleEvent, simpleEvent);
    }
}