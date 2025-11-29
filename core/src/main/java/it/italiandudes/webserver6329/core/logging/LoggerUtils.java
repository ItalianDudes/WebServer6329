package it.italiandudes.webserver6329.core.logging;

import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class LoggerUtils {

    // From ItalianDudes's IDL
    @NotNull
    public static String getStackTrace(@NotNull final Throwable throwable) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter out = new PrintWriter(stringWriter, true);
        throwable.printStackTrace(out);
        return stringWriter.getBuffer().toString();
    }
}
