package it.italiandudes.webtrpg.dnd5e.data.sheet.tabs;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import it.italiandudes.webtrpg.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webtrpg.dnd5e.data.sheet.item.DND5EItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_inventories")
@Getter
@Setter
@Check(constraints = "mc >= 0 AND ms >= 0 AND me >= 0 AND mg >= 0 AND mp >= 0")
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabInventory extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Reference
    @OneToOne(mappedBy = "tabInventory", optional = false) private DND5ESheet sheet;

    // Weight
    @Transient private double currentLoad = 0;
    @Transient private double maxLoad = 0;
    // TODO: Should I put Load% Load Category

    // Money
    @Column(name = "mc", nullable = false, columnDefinition = "INT DEFAULT 0") private int mc = 0;
    @Column(name = "ms", nullable = false, columnDefinition = "INT DEFAULT 0") private int ms = 0;
    @Column(name = "me", nullable = false, columnDefinition = "INT DEFAULT 0") private int me = 0;
    @Column(name = "mg", nullable = false, columnDefinition = "INT DEFAULT 0") private int mg = 0;
    @Column(name = "mp", nullable = false, columnDefinition = "INT DEFAULT 0") private int mp = 0;

    // Items
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id", nullable = false) private List<DND5EItem> items = new ArrayList<>();

    // Constructors
    @Builder
    public DND5ESheetTabInventory(Integer mc, Integer ms, Integer me, Integer mg, Integer mp, List<DND5EItem> items) {
        WebTRPGLogger.getLogger().debug(this.getClass().getName());
        this.mc = mc != null ? mc : 0;
        this.ms = ms != null ? ms : 0;
        this.me = me != null ? me : 0;
        this.mg = mg != null ? mg : 0;
        this.mp = mp != null ? mp : 0;
        this.items = items != null ? items : new ArrayList<>();
    }

    // Methods
    public void updateCurrentLoad() {} // TODO
    public String getLoadPercentage() {
        return String.valueOf(currentLoad / maxLoad * 100.0).formatted("#.##") + '%';
    }

    // Private Setters (Not accessible)
    private void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }
    private void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabInventory that = (DND5ESheetTabInventory) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
