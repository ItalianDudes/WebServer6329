package it.italiandudes.webserver6329.dnd5e.data.sheet.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

// DO NOT CHANGE THE ENUM NAMES, SOME METHODS USE THE FINAL METHOD "name()" FOR THE DB
@Getter
public enum DND5ELanguageProficiencyLevel {
    A1("Base"),
    A2("Elementare"),
    B1("Intermedio"),
    B2("Superiore"),
    C1("Avanzato"),
    C2("Padronanza");

    // Attributes
    @NotNull private final String desc;

    // Constructor
    DND5ELanguageProficiencyLevel(@NotNull String desc) {
        this.desc = desc;
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return this.name() + " - " + this.desc;
    }
}
