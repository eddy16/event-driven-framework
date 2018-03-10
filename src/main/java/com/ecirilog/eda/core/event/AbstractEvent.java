package com.ecirilog.eda.core.event;

/**
 * An abstract implementation of an Event.
 *
 * @author Edgar Cirilo Gonzalez &lt;proed1612@gmail.com&gt;
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractEvent implements Event {
    /**
     * Obtains the type of  concrete implementation of the event.
     * @return the class of the event.
     */
    @Override
    public Class< ? extends Event > getType() {
        return getClass();
    }
}
