package com.ecirilog.eda.core.bus.simple;

import com.ecirilog.eda.core.bus.EventBus;
import com.ecirilog.eda.core.event.Event;
import com.ecirilog.eda.core.handler.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

public class SimpleEventBus implements EventBus {
    /** Constant LOGGER instance for class {@link SimpleEventBus}.*/
    private static final Logger LOGGER = LogManager.getLogger(SimpleEventBus.class);
    /** Map reference to store the association between type and handler.*/
    private final Map<Class, EventHandler> registry;
    /** Use of ExecutorService to delegate async work to some thread.*/
    private final ExecutorService executorService;

    /**
     * Constructor for SimpleEventBus.<b/>
     * Initializes {@link Map} reference with {@link ConcurrentHashMap} instance<b/>
     * and {@link ExecutorService} with a pool of threads.
     */
    public SimpleEventBus() {
        LOGGER.debug("creating");
        this.registry = new ConcurrentHashMap<>();
        this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * Query the {@link ConcurrentHashMap} with class of incomming event<b/>
     * to retrive {@link EventHandler} and execute it.
     * @param event Incomming {@link Event} subclass.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void post(final Event event) {
        LOGGER.debug(format("post: %s", event.getType()));
        executorService.submit(() -> {
            Thread.currentThread().setName("simpleEventBus#post");
            registry.get(event.getClass()).handle(event);
        });
    }

    /**
     * Delegates each parameter from vararg to overloaded post method.
     * @param events Incomming  varargs {@link Event} subclass.
     */
    @Override
    public void post(final Event... events) {
        for (Event event: events) {
            post(event);
        }
    }

    /**
     * Store new ({@link Event}, {@link EventHandler}) associations in {@link ConcurrentHashMap} instance.<b/>
     * @param type Class type of some subclass of {@link Event}
     * @param handler Some {@link EventHandler} implementation
     */
    @Override
    public void register(final Class< ? extends Event> type, final EventHandler< ? extends Event> handler) {
        LOGGER.debug(format("register channel: %s", handler));
        executorService.submit(() -> {
            Thread.currentThread().setName("simpleEventBus#register");
            registry.putIfAbsent(type, handler);
        });
    }
}
