package it.italiandudes.webserver6329.dnd5e.data.sheet.misc;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.data.MimeImage;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ERarity;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ESpellCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_spells")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESpell extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Spell Data
    @Column(name = "name", nullable = false) private String name = "";
    @Column(name = "is_prepared", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE") private boolean isPrepared = false;
    @Column(name = "rarity", nullable = false) @Enumerated(EnumType.STRING) private DND5ERarity rarity = DND5ERarity.COMMON;
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "spell_image_id") private MimeImage spellImage = null;
    @Column(name = "spell_level", nullable = false, columnDefinition = "INT DEFAULT 0") private int spellLevel = 0;
    @Column(name = "category", nullable = false) @Enumerated(EnumType.STRING) private DND5ESpellCategory category = DND5ESpellCategory.ABJURATION;
    @Column(name = "type", nullable = false) private String type = "";
    @Column(name = "spell_time", nullable = false) private String spellTime = "";
    @Column(name = "spell_range", nullable = false) private String range = "";
    @Column(name = "components", nullable = false) private String components = "";
    @Column(name = "duration", nullable = false) private String duration = "";
    @Column(name = "effect", nullable = false) private String effect = "";
    @Column(name = "description", nullable = false) private String description = "";

    // Constructors
    @Builder
    public DND5ESpell(
            String name, Boolean isPrepared, DND5ERarity rarity, MimeImage spellImage,
            Integer spellLevel, DND5ESpellCategory category, String type,
            String spellTime, String range, String components,
            String duration, String effect, String description
    ) {
        this.name = name != null ? name : "";
        this.isPrepared = isPrepared != null ? isPrepared : false;
        this.rarity = rarity != null ? rarity : DND5ERarity.COMMON;
        this.spellImage = spellImage;
        this.spellLevel = spellLevel != null ? spellLevel : 0;
        this.category = category != null ? category : DND5ESpellCategory.ABJURATION;
        this.type = type != null ? type : "";
        this.spellTime = spellTime != null ? spellTime : "";
        this.range = range != null ? range : "";
        this.components = components != null ? components : "";
        this.duration = duration != null ? duration : "";
        this.effect = effect != null ? effect : "";
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
        DND5ESpell that = (DND5ESpell) o;
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
