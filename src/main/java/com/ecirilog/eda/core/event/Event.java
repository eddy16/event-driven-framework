package com.ecirilog.eda.core.event;

/**
 * Event is a basic representation in an event driven architecture.
 *
 * @author Edgar Cirilo Gonzalez &lt;proed1612@gmail.com&gt;
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Event {
    /**
     * Obtains the type of  concrete implementation of the event.
     * @return the class of the event.
     */
    Class< ? extends Event > getType();
}
