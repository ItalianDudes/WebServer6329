package it.italiandudes.webserver6329.dnd5e.data.sheet.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DND5EAddonSlot {
    NECKLACE("Collana"),
    MANTLE("Mantello"),
    BRACELET("Bracciale"),
    EARRING("Orecchino"),
    RING("Anello"),
    BACKPACK("Zaino"),
    BELT("Cintura"),
    BANDOLIER("Bandoliera");

    // Attributes
    @NotNull private final String name;

    // Constructor
    DND5EAddonSlot(@NotNull final String name) {
        this.name = name;
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return name;
    }
}
