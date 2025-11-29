package it.italiandudes.webserver6329.dnd5e.data.sheet.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DND5EWearableWeightCategory {
    LIGHT("Leggero (+Mod Destrezza)"),
    MEDIUM("Medio (+Mod Destrezza MAX 2)"),
    HEAVY("Pesante (Nessun Modificatore)");

    // Attributes
    @NotNull private final String name;

    // Constructor
    DND5EWearableWeightCategory(@NotNull final String name) {
        this.name = name;
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return name;
    }
}
