package it.italiandudes.webserver6329.dnd5e.data.sheet.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum DND5EArmorSlot {
    FULL_SET("Set Completo"),
    HEAD("Testa"),
    LEFT_SHOULDER("Spalla SX"),
    RIGHT_SHOULDER("Spalla DX"),
    LEFT_ARM("Braccio SX"),
    RIGHT_ARM("Braccio DX"),
    LEFT_FOREARM("Avambraccio SX"),
    RIGHT_FOREARM("Avambraccio DX"),
    LEFT_HAND("Mano SX"),
    RIGHT_HAND("Mano DX"),
    CHEST("Petto"),
    ABDOMEN("Addome"),
    BACK("Schiena"),
    LEFT_LEG("Gamba SX"),
    RIGHT_LEG("Gamba DX"),
    LEFT_KNEE("Ginocchio SX"),
    RIGHT_KNEE("Ginocchio DX"),
    LEFT_FOOT("Piede SX"),
    RIGHT_FOOT("Piede DX");

    // Attributes
    @NotNull private final String name;

    // Constructor
    DND5EArmorSlot(@NotNull final String name) {
        this.name = name;
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return name;
    }
}
