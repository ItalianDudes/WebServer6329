package it.italiandudes.webserver6329.core.misc;

import java.util.Random;

@SuppressWarnings("unused")
public final class RandomNumberGenerator {

    // RANDOM
    public static final Random RANDOM = new Random();

    // Methods
    /**
     * Randomize a number between two values, min included, max excluded
     * @param min included in range
     * @param max excluded from range
     * @return random integer in form of [min, max[
     */
    public static int randomBetween(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }
    /**
     * Randomize a number between two values, all bounds included
     * @param min included in range
     * @param max included in range
     * @return random integer in form of [min, max]
     */
    public static int randomBetweenBoundsIncluded(int min, int max) {
        return RANDOM.nextInt((max + 1) - min) + min;
    }
}
