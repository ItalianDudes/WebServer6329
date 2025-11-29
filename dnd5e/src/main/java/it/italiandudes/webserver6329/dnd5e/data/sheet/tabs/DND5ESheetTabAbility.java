package it.italiandudes.webserver6329.dnd5e.data.sheet.tabs;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EProficiencyLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_abilities")
@Getter
@Setter
@Check(constraints = "score_strength >= 0 AND score_dexterity >= 0 AND score_constitution >= 0 AND score_intelligence >= 0 AND score_wisdom >= 0 AND score_charisma >= 0")
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabAbility extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Reference
    @OneToOne(mappedBy = "tabAbility", optional = false) private DND5ESheet sheet;

    // Main Abilities
    @Min(0) @Column(name = "score_strength", columnDefinition = "INT DEFAULT 8", nullable = false) private int strength = 8;
    @Min(0) @Column(name = "score_dexterity", columnDefinition = "INT DEFAULT 8", nullable = false) private int dexterity = 8;
    @Min(0) @Column(name = "score_constitution", columnDefinition = "INT DEFAULT 8", nullable = false) private int constitution = 8;
    @Min(0) @Column(name = "score_intelligence", columnDefinition = "INT DEFAULT 8", nullable = false) private int intelligence = 8;
    @Min(0) @Column(name = "score_wisdom", columnDefinition = "INT DEFAULT 8", nullable = false) private int wisdom = 8;
    @Min(0) @Column(name = "score_charisma", columnDefinition = "INT DEFAULT 8", nullable = false) private int charisma = 8;

    // Saving Throws Proficiencies
    @Column(name = "proficiency_strength", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyStrength = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_dexterity", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyDexterity = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_constitution", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyConstitution = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_intelligence", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyIntelligence = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_wisdom", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyWisdom = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_charisma", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyCharisma = DND5EProficiencyLevel.NONE;
    
    // Ability Proficiencies
    @Column(name = "proficiency_athletics", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyAthletics = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_acrobatics", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyAcrobatics = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_stealth", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyStealth = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_sleight_of_hand", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencySleightOfHand = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_arcana", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyArcana = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_investigation", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyInvestigation = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_nature", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyNature = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_religion", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyReligion = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_history", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyHistory = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_animal_handling", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyAnimalHandling = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_insight", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyInsight = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_medicine", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyMedicine = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_perception", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyPerception = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_survival", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencySurvival = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_deception", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyDeception = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_intimidation", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyIntimidation = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_performance", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyPerformance = DND5EProficiencyLevel.NONE;
    @Column(name = "proficiency_persuasion", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyPersuasion = DND5EProficiencyLevel.NONE;

    // Constructors
    @Builder
    public DND5ESheetTabAbility(
            Integer strength, Integer dexterity, Integer constitution, Integer intelligence, Integer wisdom, Integer charisma,
            DND5EProficiencyLevel proficiencyStrength, DND5EProficiencyLevel proficiencyDexterity, DND5EProficiencyLevel proficiencyConstitution,
            DND5EProficiencyLevel proficiencyIntelligence, DND5EProficiencyLevel proficiencyWisdom, DND5EProficiencyLevel proficiencyCharisma,
            DND5EProficiencyLevel proficiencyAthletics, DND5EProficiencyLevel proficiencyAcrobatics, DND5EProficiencyLevel proficiencyStealth,
            DND5EProficiencyLevel proficiencySleightOfHand, DND5EProficiencyLevel proficiencyArcana, DND5EProficiencyLevel proficiencyInvestigation,
            DND5EProficiencyLevel proficiencyNature, DND5EProficiencyLevel proficiencyReligion, DND5EProficiencyLevel proficiencyHistory,
            DND5EProficiencyLevel proficiencyAnimalHandling, DND5EProficiencyLevel proficiencyInsight, DND5EProficiencyLevel proficiencyMedicine,
            DND5EProficiencyLevel proficiencyPerception, DND5EProficiencyLevel proficiencySurvival, DND5EProficiencyLevel proficiencyDeception,
            DND5EProficiencyLevel proficiencyIntimidation, DND5EProficiencyLevel proficiencyPerformance, DND5EProficiencyLevel proficiencyPersuasion
    ) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.strength = strength != null ? strength : 8;
        this.dexterity = dexterity != null ? dexterity : 8;
        this.constitution = constitution != null ? constitution : 8;
        this.intelligence = intelligence != null ? intelligence : 8;
        this.wisdom = wisdom != null ? wisdom : 8;
        this.charisma = charisma != null ? charisma : 8;

        this.proficiencyStrength = proficiencyStrength != null ? proficiencyStrength : DND5EProficiencyLevel.NONE;
        this.proficiencyDexterity = proficiencyDexterity != null ? proficiencyDexterity : DND5EProficiencyLevel.NONE;
        this.proficiencyConstitution = proficiencyConstitution != null ? proficiencyConstitution : DND5EProficiencyLevel.NONE;
        this.proficiencyIntelligence = proficiencyIntelligence != null ? proficiencyIntelligence : DND5EProficiencyLevel.NONE;
        this.proficiencyWisdom = proficiencyWisdom != null ? proficiencyWisdom : DND5EProficiencyLevel.NONE;
        this.proficiencyCharisma = proficiencyCharisma != null ? proficiencyCharisma : DND5EProficiencyLevel.NONE;

        this.proficiencyAthletics = proficiencyAthletics != null ? proficiencyAthletics : DND5EProficiencyLevel.NONE;
        this.proficiencyAcrobatics = proficiencyAcrobatics != null ? proficiencyAcrobatics : DND5EProficiencyLevel.NONE;
        this.proficiencyStealth = proficiencyStealth != null ? proficiencyStealth : DND5EProficiencyLevel.NONE;
        this.proficiencySleightOfHand = proficiencySleightOfHand != null ? proficiencySleightOfHand : DND5EProficiencyLevel.NONE;
        this.proficiencyArcana = proficiencyArcana != null ? proficiencyArcana : DND5EProficiencyLevel.NONE;
        this.proficiencyInvestigation = proficiencyInvestigation != null ? proficiencyInvestigation : DND5EProficiencyLevel.NONE;
        this.proficiencyNature = proficiencyNature != null ? proficiencyNature : DND5EProficiencyLevel.NONE;
        this.proficiencyReligion = proficiencyReligion != null ? proficiencyReligion : DND5EProficiencyLevel.NONE;
        this.proficiencyHistory = proficiencyHistory != null ? proficiencyHistory : DND5EProficiencyLevel.NONE;
        this.proficiencyAnimalHandling = proficiencyAnimalHandling != null ? proficiencyAnimalHandling : DND5EProficiencyLevel.NONE;
        this.proficiencyInsight = proficiencyInsight != null ? proficiencyInsight : DND5EProficiencyLevel.NONE;
        this.proficiencyMedicine = proficiencyMedicine != null ? proficiencyMedicine : DND5EProficiencyLevel.NONE;
        this.proficiencyPerception = proficiencyPerception != null ? proficiencyPerception : DND5EProficiencyLevel.NONE;
        this.proficiencySurvival = proficiencySurvival != null ? proficiencySurvival : DND5EProficiencyLevel.NONE;
        this.proficiencyDeception = proficiencyDeception != null ? proficiencyDeception : DND5EProficiencyLevel.NONE;
        this.proficiencyIntimidation = proficiencyIntimidation != null ? proficiencyIntimidation : DND5EProficiencyLevel.NONE;
        this.proficiencyPerformance = proficiencyPerformance != null ? proficiencyPerformance : DND5EProficiencyLevel.NONE;
        this.proficiencyPersuasion = proficiencyPersuasion != null ? proficiencyPersuasion : DND5EProficiencyLevel.NONE;
    }
    
    // Methods
    public static int getMainAbilityModifier(int score) {
        return (int) Math.floor((score-10.0)/2.0);
    }
    public int getStrengthModifier() {
        return getMainAbilityModifier(strength);
    }
    public int getDexterityModifier() {
        return getMainAbilityModifier(dexterity);
    }
    public int getConstitutionModifier() {
        return getMainAbilityModifier(constitution);
    }
    public int getIntelligenceModifier() {
        return getMainAbilityModifier(intelligence);
    }
    public int getWisdomModifier() {
        return getMainAbilityModifier(wisdom);
    }
    public int getCharismaModifier() {
        return getMainAbilityModifier(charisma);
    }
    public int getPassiveWisdom() {
        return 10 + proficiencyPerception.getEffectiveProficiencyBonus(getWisdomModifier(), sheet.getTabCharacter().getProficiencyBonus());
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabAbility that = (DND5ESheetTabAbility) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
