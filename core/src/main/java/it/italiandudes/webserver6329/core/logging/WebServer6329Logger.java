package it.italiandudes.webserver6329.core.logging;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebServer6329Logger {

    // Attributes
    @NotNull private final Logger LOGGER;

    // Constructor
    private WebServer6329Logger() {
        LOGGER = LoggerFactory.getLogger("WebServer6329");
    }

    // Instance
    private static WebServer6329Logger INSTANCE;
    @NotNull
    public static Logger getLogger() {
        if (INSTANCE == null) INSTANCE = new WebServer6329Logger();
        return INSTANCE.LOGGER;
    }
}
