package it.italiandudes.webserver6329.dnd5e.data.sheet.tabs;

import it.italiandudes.webserver6329.dnd5e.data.sheet.item.*;
import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EEquipmentCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EItemCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.item.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_equipments")
@Getter
@Setter
// @NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabEquipment extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Reference
    @OneToOne(mappedBy = "tabEquipment", optional = false) private DND5ESheet sheet;

    // Equipped Pieces
    @Transient private DND5EDress equippedDress = null;
    @Transient private final ArrayList<DND5EArmor> equippedArmors = new ArrayList<>();
    @Transient private final ArrayList<DND5EAddon> equippedAddons = new ArrayList<>();
    @Transient private final ArrayList<DND5EWeapon> equippedWeapons = new ArrayList<>();

    // Constructors
    @Builder
    public DND5ESheetTabEquipment() {}

    // Methods
    public void updateEquippedPieces() {
        List<DND5EEquipment> equippedEquipments = sheet.getTabInventory().getItems().stream().filter(dnd5EItem -> dnd5EItem.getItemCategory() == DND5EItemCategory.EQUIPMENT).map(item -> (DND5EEquipment) item).filter(DND5EEquipment::isEquipped).toList();
        equippedArmors.clear();
        equippedAddons.clear();
        equippedWeapons.clear();
        equippedDress = equippedEquipments.stream().filter(dnd5EEquipment -> dnd5EEquipment.getEquipmentCategory() == DND5EEquipmentCategory.DRESS).map(equipment -> (DND5EDress) equipment).findFirst().orElse(null);
        equippedArmors.addAll(equippedEquipments.stream().filter(equipment -> equipment.getEquipmentCategory() == DND5EEquipmentCategory.ARMOR).map(equipment -> (DND5EArmor) equipment).toList());
        equippedAddons.addAll(equippedEquipments.stream().filter(equipment -> equipment.getEquipmentCategory() == DND5EEquipmentCategory.ADDON).map(equipment -> (DND5EAddon) equipment).toList());
        equippedWeapons.addAll(equippedEquipments.stream().filter(equipment -> equipment.getEquipmentCategory() == DND5EEquipmentCategory.WEAPON).map(equipment -> (DND5EWeapon) equipment).toList());
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabEquipment that = (DND5ESheetTabEquipment) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
