package it.italiandudes.webserver6329.dnd5e.data.sheet.proficiency;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ELanguageProficiencyLevel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_language_proficiencies")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ELanguageProficiency extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Attributes
    @Column(name = "name", nullable = false) private String name = "";
    @Column(name = "alphabet_name", nullable = false) private String alphabetName = "";
    @Column(name = "reading", nullable = false) @Enumerated(EnumType.STRING) private DND5ELanguageProficiencyLevel proficiencyReading = DND5ELanguageProficiencyLevel.A1;
    @Column(name = "writing", nullable = false) @Enumerated(EnumType.STRING) private DND5ELanguageProficiencyLevel proficiencyWriting = DND5ELanguageProficiencyLevel.A1;
    @Column(name = "listening", nullable = false) @Enumerated(EnumType.STRING) private DND5ELanguageProficiencyLevel proficiencyListening = DND5ELanguageProficiencyLevel.A1;
    @Column(name = "speaking", nullable = false) @Enumerated(EnumType.STRING) private DND5ELanguageProficiencyLevel proficiencySpeaking = DND5ELanguageProficiencyLevel.A1;

    // Constructors
    @Builder
    public DND5ELanguageProficiency(
            String name, String alphabetName,
            DND5ELanguageProficiencyLevel proficiencyReading,
            DND5ELanguageProficiencyLevel proficiencyWriting,
            DND5ELanguageProficiencyLevel proficiencyListening,
            DND5ELanguageProficiencyLevel proficiencySpeaking
    ) {
        this.name = name != null ? name : "";
        this.alphabetName = alphabetName != null ? alphabetName : "";
        this.proficiencyReading = proficiencyReading != null ? proficiencyReading : DND5ELanguageProficiencyLevel.A1;
        this.proficiencyWriting = proficiencyWriting != null ? proficiencyWriting : DND5ELanguageProficiencyLevel.A1;
        this.proficiencyListening = proficiencyListening != null ? proficiencyListening : DND5ELanguageProficiencyLevel.A1;
        this.proficiencySpeaking = proficiencySpeaking != null ? proficiencySpeaking : DND5ELanguageProficiencyLevel.A1;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ELanguageProficiency that = (DND5ELanguageProficiency) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return "\"" + name + "\" - " + proficiencyReading.toString() + " - " + proficiencyWriting.toString() + " - " + proficiencyListening.toString() + " - " + proficiencySpeaking.toString();
    }
}
