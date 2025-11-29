package it.italiandudes.webserver6329.dnd5e.data.sheet.tabs;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.data.MimeImage;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.DND5ESheet;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_characters")
@Check(constraints = "level >= 1 AND exp >= 0 AND max_hp >= 1 AND current_hp <= max_hp AND life_dice_faces >= 1 AND life_dice_total_amount >= 1 AND life_dice_current_amount >= 0 AND proficiency_bonus >= 0 AND inspiration_points >= 0")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabCharacter extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Reference
    @OneToOne(mappedBy = "tabCharacter", optional = false) private DND5ESheet sheet;

    // Sheet Header
    @Column(name = "character_name", nullable = false) private String characterName = "";
    @Min(1) @Column(name = "level", columnDefinition = "INT DEFAULT 1", nullable = false) private int level = 1;
    @Column(name = "character_class", nullable = false) private String characterClass = "";
    @Column(name = "background", nullable = false) private String background = "";
    @Column(name = "race", nullable = false) private String race = "";
    @Column(name = "alignment", nullable = false) private String alignment = "";
    @Min(0) @Column(name = "exp", columnDefinition = "INT DEFAULT 0", nullable = false) private int exp = 0;
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "character_image_id") private MimeImage characterImage = null;

    // Life
    // @Builder.Default @Min(1) @Transient private int calculatedMaxHP = 1;
    @Min(1) @Column(name = "max_hp", columnDefinition = "INT DEFAULT 20", nullable = false) private int maxHP = 20;
    @Column(name = "current_hp", columnDefinition = "INT DEFAULT 20", nullable = false) private int currentHP = 20;
    @Column(name = "temp_hp", columnDefinition = "INT DEFAULT 0", nullable = false) private int tempHP = 0;
    @Min(1) @Column(name = "life_dice_faces", columnDefinition = "INT DEFAULT 20", nullable = false) private int lifeDiceFaces = 20;
    @Min(1) @Column(name = "life_dice_total_amount", columnDefinition = "INT DEFAULT 1", nullable = false) private int lifeDiceTotalAmount = 1;
    @Min(0) @Column(name = "life_dice_current_amount", columnDefinition = "INT DEFAULT 1", nullable = false) private int lifeDiceCurrentAmount = 1;

    // Proficiency Bonus and Secondary Stats
    @Min(0) @Column(name = "proficiency_bonus", columnDefinition = "INT DEFAULT 2", nullable = false) private int proficiencyBonus = 2;
    @Column(name = "speed", nullable = false) private String speed = "";
    @Min(0) @Column(name = "inspiration_points", columnDefinition = "INT DEFAULT 0", nullable = false) private int inspirationPoints = 0;

    // ST Against Death
    @Min(0) @Max(3) @Column(name = "success_death_st", columnDefinition = "INT DEFAULT 0", nullable = false) private int successDeathST = 0;
    @Min(0) @Max(3) @Column(name = "fail_death_st", columnDefinition = "INT DEFAULT 0", nullable = false) private int failDeathST = 0;

    // Character Info
    @Column(name = "personal_traits", nullable = false) private String personalTraits = "";
    @Column(name = "bonds", nullable = false) private String bonds = "";
    @Column(name = "ideals", nullable = false) private String ideals = "";
    @Column(name = "flaws", nullable = false) private String flaws = "";

    // Constructor
    @Builder
    public DND5ESheetTabCharacter(
            String characterName, Integer level, String characterClass, String background, String race,
            String alignment, Integer exp, MimeImage characterImage, Integer maxHP, Integer currentHP,
            Integer tempHP, Integer lifeDiceFaces, Integer lifeDiceTotalAmount, Integer lifeDiceCurrentAmount,
            Integer proficiencyBonus, String speed, Integer inspirationPoints, Integer successDeathST,
            Integer failDeathST, String personalTraits, String bonds, String ideals, String flaws) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.characterName = characterName != null ? characterName : "";
        this.level = level != null ? level : 1;
        this.characterClass = characterClass != null ? characterClass : "";
        this.background = background != null ? background : "";
        this.race = race != null ? race : "";
        this.alignment = alignment != null ? race : "";
        this.exp = exp != null ? exp : 0;
        this.characterImage = characterImage;
        this.maxHP = maxHP != null ? maxHP : 20;
        this.currentHP = currentHP != null ? currentHP : 20;
        this.tempHP = tempHP != null ? tempHP : 0;
        this.lifeDiceFaces = lifeDiceFaces != null ? lifeDiceFaces : 20;
        this.lifeDiceTotalAmount = lifeDiceTotalAmount != null ? lifeDiceTotalAmount : 1;
        this.lifeDiceCurrentAmount = lifeDiceCurrentAmount != null ? lifeDiceCurrentAmount : 1;
        this.proficiencyBonus = proficiencyBonus != null ? proficiencyBonus : 2;
        this.speed = speed != null ? speed : "";
        this.inspirationPoints = inspirationPoints != null ? inspirationPoints : 0;
        this.successDeathST = successDeathST != null ? successDeathST : 0;
        this.failDeathST = failDeathST != null ? failDeathST : 0;
        this.personalTraits = personalTraits != null ? personalTraits : "";
        this.bonds = bonds != null ? bonds : "";
        this.ideals = ideals != null ? ideals : "";
        this.flaws = flaws != null ? flaws : "";
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabCharacter that = (DND5ESheetTabCharacter) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return (characterName != null && !characterName.isBlank() ? characterName : "SENZA NOME") + " - " + level;
    }
}
