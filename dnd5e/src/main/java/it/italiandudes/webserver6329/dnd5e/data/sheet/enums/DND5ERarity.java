package it.italiandudes.webserver6329.dnd5e.data.sheet.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DND5ERarity {
    COMMON("Comune", "#E0E0E0"),
    UNCOMMON("Non Comune", "#22A318"),
    RARE("Raro", "#098CDE"),
    VERY_RARE("Molto Raro", "#275EEB"),
    LEGENDARY("Leggendario", "#B511AB"),
    EXOTIC("Esotico", "#E8E409");

    // Attributes
    @NotNull private final String textedRarity;
    @NotNull private final String rgbColor;

    // Constructor
    DND5ERarity(@NotNull final String textedRarity, @NotNull final String rgbColor) {
        this.textedRarity = textedRarity;
        this.rgbColor = rgbColor;
    }

    // ToString
    @Override
    public String toString() {
        return textedRarity;
    }
}
