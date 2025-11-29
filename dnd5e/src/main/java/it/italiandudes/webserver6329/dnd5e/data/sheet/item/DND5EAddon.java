package it.italiandudes.webserver6329.dnd5e.data.sheet.item;

import it.italiandudes.webserver6329.core.data.MimeImage;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EAddonSlot;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EEquipmentCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ERarity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dnd5e_addons")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EAddon extends DND5EEquipment {

    // Armor Data
    @Column(name = "slot", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'NECKLACE'") @Enumerated(EnumType.STRING) private DND5EAddonSlot slot = DND5EAddonSlot.NECKLACE;

    // Constructors
    public DND5EAddon(
            String name, MimeImage itemImage, DND5ERarity rarity, Double weight, Integer quantity, Integer costMR, String description,
            Integer caEffect, Integer lifeEffect, Integer loadEffect, Double lifeEffectPercentage, Double loadEffectPercentage, String otherEffects, Boolean isEquipped,
            DND5EAddonSlot slot
    ) {
        super(name, itemImage, rarity, weight, quantity, costMR, description, DND5EEquipmentCategory.ADDON, caEffect, lifeEffect, loadEffect, lifeEffectPercentage, loadEffectPercentage, otherEffects, isEquipped);
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.slot = slot != null ? slot : DND5EAddonSlot.NECKLACE;
    }
}
