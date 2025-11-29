package it.italiandudes.webserver6329.dnd5e.data.sheet.misc;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_dice_rolls")
@Getter
@Check(constraints = "face_count >= 2 AND roll > 0")
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EDiceRoll extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Attributes
    @Column(name = "face_count", nullable = false, columnDefinition = "INT DEFAULT 20") private int faceCount = 20;
    @Column(name = "roll", nullable = false, columnDefinition = "INT DEFAULT 1") private int roll = 1;

    // Constructors
    @Builder
    public DND5EDiceRoll(Integer faceCount, Integer roll) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.faceCount = faceCount != null ? faceCount : 20;
        this.roll = roll != null ? roll : 1;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5EDiceRoll that = (DND5EDiceRoll) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return "d" + faceCount + " = " + roll;
    }
}
