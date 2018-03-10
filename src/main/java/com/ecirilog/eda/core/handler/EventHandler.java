package com.ecirilog.eda.core.handler;

import com.ecirilog.eda.core.event.Event;

/**
 * {@link EventHandler} interface provides a method signature to manage the generic event parameter.<b/>
 * Handlers act as destination points for receiving events.
 * @author Edgar Cirilo Gonzalez &lt;proed1612@gmail.com&gt;
 * @version 1.0.0
 * @param <E> Covariance param, E is a subclass of {@link Event}
 * @since 1.0.0
 */
public interface EventHandler < E extends Event > {
    /**
     * Defines what to do with the event.
     * @param event {@link Event} based POJO parameter.
     */
    void handle(E event);
}
