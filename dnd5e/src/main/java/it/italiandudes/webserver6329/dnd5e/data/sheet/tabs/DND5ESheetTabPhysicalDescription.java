package it.italiandudes.webserver6329.dnd5e.data.sheet.tabs;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.DND5ESheet;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_physical_descriptions")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabPhysicalDescription extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Reference
    @OneToOne(mappedBy = "tabPhysicalDescription", optional = false) private DND5ESheet sheet;

    // Sheet Header
    @Column(name = "age", nullable = false) private String age = "";
    @Column(name = "height", nullable = false) private String height = "";
    @Column(name = "weight", nullable = false) private String weight = "";
    @Column(name = "eyes", nullable = false) private String eyes = "";
    @Column(name = "skin", nullable = false) private String skin = "";
    @Column(name = "hair", nullable = false) private String hair = "";
    @Column(name = "extended_physical_description", nullable = false) private String extendedPhysicalDescription = "";

    // Constructor
    @Builder
    public DND5ESheetTabPhysicalDescription(
            String age, String height, String weight,
            String eyes, String skin, String hair,
            String extendedPhysicalDescription
    ) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.age = age != null ? age : "";
        this.height = height != null ? height : "";
        this.weight = weight != null ? weight : "";
        this.eyes = eyes != null ? eyes : "";
        this.skin = skin != null ? skin : "";
        this.hair = hair != null ? hair : "";
        this.extendedPhysicalDescription = extendedPhysicalDescription != null ? extendedPhysicalDescription : "";
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabPhysicalDescription that = (DND5ESheetTabPhysicalDescription) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
