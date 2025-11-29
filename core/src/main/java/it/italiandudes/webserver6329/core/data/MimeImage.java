package it.italiandudes.webserver6329.core.data;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "mime_images")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
public class MimeImage extends AuditableEntity {

    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "image_extension", nullable = false, updatable = false) private String imageExtension;
    @Column(name = "base64image", nullable = false, updatable = false) private String base64image;

    // Constructor
    @Builder
    public MimeImage(@NotNull final String imageExtension, @NotNull final String base64image) {
        this.imageExtension = Objects.requireNonNull(imageExtension);
        this.base64image = Objects.requireNonNull(base64image);
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MimeImage mimeImage = (MimeImage) o;
        return getId() != null && Objects.equals(getId(), mimeImage.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @NotNull @Override
    public String toString() {
        return "data:image/" + imageExtension + ";base64," + base64image;
    }
}
