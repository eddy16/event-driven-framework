package com.ecirilog.eda.core.bus;

import com.ecirilog.eda.core.event.Event;
import com.ecirilog.eda.core.handler.EventHandler;

/**
 * {@link EventBus} are responsible for selecting the proper path for a given event.<br/>
 * <p>{@link EventBus} will register a {@link Event} with an associated {@link EventHandler}.
 * All {@link Event} subclasses posted by the {@link EventBus} should automatically match
 * its type with its associated {@link EventHandler} and route the {@link Event} to it.</p>
 * @author Edgar Cirilo Gonzalez &lt;proed1612@gmail.com&gt;
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EventBus {
    /**
     * Must automatically route the {@link Event} to its {@link EventHandler}.
     * @param event Incomming {@link Event}
     */
    void post(Event event);

    /**
     * Must automatically route the message to its {@link EventHandler}.
     * @param events Incomming vararg {@link Event}
     */
    void post(Event... events);

    /**
     * Takes a {@link Event} type and associates it with its {@link EventHandler}.
     * @param type Class type of some subclass of {@link Event}
     * @param handler Some {@link EventHandler} implementation
     * @see Class
     * @see EventHandler
     */
    void register(Class< ? extends Event > type, EventHandler< ? extends Event > handler);
}
