package it.italiandudes.webtrpg.dnd5e.data.sheet.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

// DO NOT CHANGE THE ENUM NAMES, SOME METHODS USE THE FINAL METHOD "name()" FOR THE DB
@Getter
public enum DND5EProficiencyLevel {
    SEVERE_INEPTITUDE("Grave Incompetenza"),
    INEPTITUDE("Incompetenza"),
    NONE("Nessuna"),
    PROFICIENCY("Competenza"),
    MASTERY("Maestria");

    // Attributes
    private final String readableName;

    // Constructor
    DND5EProficiencyLevel(String readableName) {
        this.readableName = readableName;
    }

    // Methods
    public int getEffectiveProficiencyBonus(int modifier, int characterProficiencyBonus) {
        switch (this) {
            case SEVERE_INEPTITUDE -> {
                return modifier - 2;
            }
            case INEPTITUDE -> {
                return modifier - 1;
            }
            case PROFICIENCY -> {
                return modifier + characterProficiencyBonus;
            }
            case MASTERY -> {
                return modifier + characterProficiencyBonus*2;
            }
            default -> {
                return modifier;
            }
        }
    }
    @Override @NotNull
    public String toString() {
        return readableName;
    }
}
