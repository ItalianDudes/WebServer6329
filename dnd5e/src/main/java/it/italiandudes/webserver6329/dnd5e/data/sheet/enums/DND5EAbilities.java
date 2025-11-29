package it.italiandudes.webserver6329.dnd5e.data.sheet.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@SuppressWarnings("unused")
public enum DND5EAbilities {
    ATHLETICS(DND5EMainAbilities.STRENGTH),
    ACROBATICS(DND5EMainAbilities.DEXTERITY),
    STEALTH(DND5EMainAbilities.DEXTERITY),
    SLEIGHT_OF_HAND(DND5EMainAbilities.DEXTERITY),
    ARCANA(DND5EMainAbilities.INTELLIGENCE),
    INVESTIGATION(DND5EMainAbilities.INTELLIGENCE),
    NATURE(DND5EMainAbilities.INTELLIGENCE),
    RELIGION(DND5EMainAbilities.INTELLIGENCE),
    HISTORY(DND5EMainAbilities.INTELLIGENCE),
    ANIMAL_HANDLING(DND5EMainAbilities.WISDOM),
    INSIGHT(DND5EMainAbilities.WISDOM),
    MEDICINE(DND5EMainAbilities.WISDOM),
    PERCEPTION(DND5EMainAbilities.WISDOM),
    SURVIVAL(DND5EMainAbilities.WISDOM),
    DECEPTION(DND5EMainAbilities.CHARISMA),
    INTIMIDATION(DND5EMainAbilities.CHARISMA),
    PERFORMANCE(DND5EMainAbilities.CHARISMA),
    PERSUASION(DND5EMainAbilities.CHARISMA);

    // Attributes
    @NotNull private final DND5EMainAbilities mainAbility;

    // Constructor
    DND5EAbilities(@NotNull final DND5EMainAbilities mainAbility) {
        this.mainAbility = mainAbility;
    }
}
