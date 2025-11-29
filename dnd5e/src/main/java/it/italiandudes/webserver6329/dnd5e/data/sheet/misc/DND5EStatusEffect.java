package it.italiandudes.webserver6329.dnd5e.data.sheet.misc;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EStatusEffectKnowledge;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EStatusEffectNature;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EStatusEffectSource;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_status_effects")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EStatusEffect extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Attributes
    @Column(name = "name", nullable = false) private String name = "";
    @Column(name = "nature", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'UNKNOWN'") @Enumerated(EnumType.STRING) private DND5EStatusEffectNature nature = DND5EStatusEffectNature.UNKNOWN;
    @Column(name = "source", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'UNKNOWN'") @Enumerated(EnumType.STRING) private DND5EStatusEffectSource source = DND5EStatusEffectSource.UNKNOWN;
    @Column(name = "is_active", nullable = false) private boolean isActive = false;
    @Column(name = "is_curable", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'UNKNOWN'") @Enumerated(EnumType.STRING) private DND5EStatusEffectKnowledge isCurable = DND5EStatusEffectKnowledge.UNKNOWN;
    @Column(name = "is_treatable", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'UNKNOWN'") @Enumerated(EnumType.STRING) private DND5EStatusEffectKnowledge isTreatable = DND5EStatusEffectKnowledge.UNKNOWN;
    @Column(name = "is_lethal", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'UNKNOWN'") @Enumerated(EnumType.STRING) private DND5EStatusEffectKnowledge isLethal = DND5EStatusEffectKnowledge.UNKNOWN;
    @Column(name = "duration", nullable = false) private String duration = "";
    @Column(name = "ca_effect", nullable = false, columnDefinition = "INT DEFAULT 0") private int caEffect = 0;
    @Column(name = "life_effect", nullable = false, columnDefinition = "INT DEFAULT 0") private int lifeEffect = 0;
    @Column(name = "load_effect", nullable = false, columnDefinition = "INT DEFAULT 0") private int loadEffect = 0;
    @Column(name = "life_effect_perc", nullable = false, columnDefinition = "INT DEFAULT 0") private double lifeEffectPercentage = 0;
    @Column(name = "load_effect_perc", nullable = false, columnDefinition = "INT DEFAULT 0") private double loadEffectPercentage = 0;
    @Column(name = "other_effects", nullable = false) private String otherEffects = "";
    @Column(name = "description", nullable = false) private String description = "";

    // Constructors
    @Builder
    public DND5EStatusEffect(
            String name, DND5EStatusEffectNature nature,
            DND5EStatusEffectSource source, Boolean isActive,
            DND5EStatusEffectKnowledge isCurable,
            DND5EStatusEffectKnowledge isTreatable,
            DND5EStatusEffectKnowledge isLethal,
            String duration, Integer caEffect,
            Integer lifeEffect, Integer loadEffect,
            Double lifeEffectPercentage, Double loadEffectPercentage,
            String otherEffects, String description
    ) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.name = name != null ? name : "";
        this.nature = nature != null ? nature : DND5EStatusEffectNature.UNKNOWN;
        this.source = source != null ? source : DND5EStatusEffectSource.UNKNOWN;
        this.isActive = isActive != null ? isActive : false;
        this.isCurable = isCurable != null ? isCurable : DND5EStatusEffectKnowledge.UNKNOWN;
        this.isTreatable = isTreatable != null ? isTreatable : DND5EStatusEffectKnowledge.UNKNOWN;
        this.isLethal = isLethal != null ? isLethal : DND5EStatusEffectKnowledge.UNKNOWN;
        this.duration = duration != null ? duration : "";
        this.caEffect = caEffect != null ? caEffect : 0;
        this.lifeEffect = lifeEffect != null ? lifeEffect : 0;
        this.loadEffect = loadEffect != null ? loadEffect : 0;
        this.lifeEffectPercentage = lifeEffectPercentage != null ? lifeEffectPercentage : 0;
        this.loadEffectPercentage = loadEffectPercentage != null ? loadEffectPercentage : 0;
        this.otherEffects = otherEffects != null ? otherEffects : "";
        this.description = description != null ? description : "";
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5EStatusEffect that = (DND5EStatusEffect) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return name;
    }
}
