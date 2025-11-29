package it.italiandudes.webserver6329.dnd5e.data.sheet.item;

import it.italiandudes.webserver6329.core.data.MimeImage;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EArmorSlot;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EEquipmentCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ERarity;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EWearableWeightCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dnd5e_armors")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EArmor extends DND5EEquipment {

    // Armor Data
    @Column(name = "slot", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'FULL_SET'") @Enumerated(EnumType.STRING) private DND5EArmorSlot slot = DND5EArmorSlot.FULL_SET;
    @Column(name = "weight_category", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'LIGHT'") @Enumerated(EnumType.STRING) private DND5EWearableWeightCategory weightCategory = DND5EWearableWeightCategory.LIGHT;

    // Constructors
    public DND5EArmor(
            String name, MimeImage itemImage, DND5ERarity rarity, Double weight, Integer quantity, Integer costMR, String description,
            Integer caEffect, Integer lifeEffect, Integer loadEffect, Double lifeEffectPercentage, Double loadEffectPercentage, String otherEffects, Boolean isEquipped,
            DND5EArmorSlot slot, DND5EWearableWeightCategory weightCategory
    ) {
        super(name, itemImage, rarity, weight, quantity, costMR, description, DND5EEquipmentCategory.ARMOR, caEffect, lifeEffect, loadEffect, lifeEffectPercentage, loadEffectPercentage, otherEffects, isEquipped);
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.slot = slot != null ? slot : DND5EArmorSlot.FULL_SET;
        this.weightCategory = weightCategory != null ? weightCategory : DND5EWearableWeightCategory.LIGHT;
    }
}
