package it.italiandudes.webserver6329.dnd5e.data.sheet.tabs;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webserver6329.dnd5e.data.sheet.misc.DND5EClassPrivilege;
import it.italiandudes.webserver6329.dnd5e.data.sheet.misc.DND5ETrait;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_privileges_and_traits")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabPrivilegesAndTraits extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Reference
    @OneToOne(mappedBy = "tabPrivilegesAndTraits", optional = false) private DND5ESheet sheet;

    // Privileges & Traits
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id", nullable = false) private List<DND5EClassPrivilege> classPrivileges = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id", nullable = false) private List<DND5ETrait> traits = new ArrayList<>();

    // Constructors
    @Builder
    public DND5ESheetTabPrivilegesAndTraits(List<DND5EClassPrivilege> classPrivileges, List<DND5ETrait> traits) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.classPrivileges = classPrivileges != null ? classPrivileges : new ArrayList<>();
        this.traits = traits != null ? traits : new ArrayList<>();
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabPrivilegesAndTraits that = (DND5ESheetTabPrivilegesAndTraits) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
