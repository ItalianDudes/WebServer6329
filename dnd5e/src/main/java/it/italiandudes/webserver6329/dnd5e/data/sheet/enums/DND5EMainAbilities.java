package it.italiandudes.webserver6329.dnd5e.data.sheet.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DND5EMainAbilities {
    STRENGTH("Forza"),
    DEXTERITY("Destrezza"),
    CONSTITUTION("Costituzione"),
    INTELLIGENCE("Intelligenza"),
    WISDOM("Saggezza"),
    CHARISMA("Carisma");

    // Attributes
    private final String readableName;

    // Constructor
    DND5EMainAbilities(String readableName) {
        this.readableName = readableName;
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return readableName;
    }

}
