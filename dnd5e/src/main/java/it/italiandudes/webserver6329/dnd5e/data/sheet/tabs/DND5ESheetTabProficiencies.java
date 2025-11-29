package it.italiandudes.webserver6329.dnd5e.data.sheet.tabs;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webserver6329.dnd5e.data.sheet.proficiency.DND5EActivityProficiency;
import it.italiandudes.webserver6329.dnd5e.data.sheet.proficiency.DND5EArmorProficiency;
import it.italiandudes.webserver6329.dnd5e.data.sheet.proficiency.DND5EToolProficiency;
import it.italiandudes.webserver6329.dnd5e.data.sheet.proficiency.DND5EWeaponProficiency;
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
@Table(name = "dnd5e_sheet_tabs_proficiencies")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabProficiencies extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Reference
    @OneToOne(mappedBy = "tabProficiencies", optional = false) private DND5ESheet sheet;

    // Proficiencies
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id", nullable = false) private List<DND5EArmorProficiency> armors = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id", nullable = false) private List<DND5EWeaponProficiency> weapons = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id", nullable = false) private List<DND5EToolProficiency> tools = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id", nullable = false) private List<DND5EActivityProficiency> activities = new ArrayList<>();

    // Constructors
    @Builder
    public DND5ESheetTabProficiencies(
            List<DND5EArmorProficiency> armors, List<DND5EWeaponProficiency> weapons,
            List<DND5EToolProficiency> tools, List<DND5EActivityProficiency> activities
    ) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.armors = armors != null ? armors : new ArrayList<>();
        this.weapons = weapons != null ? weapons : new ArrayList<>();
        this.tools = tools != null ? tools : new ArrayList<>();
        this.activities = activities != null ? activities : new ArrayList<>();
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabProficiencies that = (DND5ESheetTabProficiencies) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
