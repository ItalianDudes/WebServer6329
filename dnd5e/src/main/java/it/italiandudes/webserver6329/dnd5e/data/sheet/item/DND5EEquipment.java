package it.italiandudes.webserver6329.dnd5e.data.sheet.item;

import it.italiandudes.webserver6329.core.data.MimeImage;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EEquipmentCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EItemCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ERarity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_equipments")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
public abstract class DND5EEquipment extends DND5EItem {

    // Equipment Data
    @Column(name = "equipment_category", nullable = false) @Enumerated(EnumType.STRING) private DND5EEquipmentCategory equipmentCategory;
    @Column(name = "ca_effect", nullable = false, columnDefinition = "INT DEFAULT 0") private int caEffect = 0;
    @Column(name = "life_effect", nullable = false, columnDefinition = "INT DEFAULT 0") private int lifeEffect = 0;
    @Column(name = "load_effect", nullable = false, columnDefinition = "INT DEFAULT 0") private int loadEffect = 0;
    @Column(name = "life_effect_perc", nullable = false, columnDefinition = "INT DEFAULT 0") private double lifeEffectPercentage = 0;
    @Column(name = "load_effect_perc", nullable = false, columnDefinition = "INT DEFAULT 0") private double loadEffectPercentage = 0;
    @Column(name = "other_effects", nullable = false) private String otherEffects = "";
    @Column(name = "is_equipped", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE") private boolean isEquipped = false; // TODO: Replace this with EquipmentSlot

    // Constructors
    public DND5EEquipment(
            String name, MimeImage itemImage, DND5ERarity rarity, Double weight, Integer quantity, Integer costMR, String description,
            DND5EEquipmentCategory equipmentCategory, Integer caEffect, Integer lifeEffect, Integer loadEffect, Double lifeEffectPercentage, Double loadEffectPercentage, String otherEffects, Boolean isEquipped
    ) {
        super(name, DND5EItemCategory.EQUIPMENT, itemImage, rarity, weight, quantity, costMR, description);
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.equipmentCategory = Objects.requireNonNull(equipmentCategory);
        this.caEffect = caEffect != null ? caEffect : 0;
        this.lifeEffect = lifeEffect != null ? lifeEffect : 0;
        this.loadEffect = loadEffect != null ? loadEffect : 0;
        this.lifeEffectPercentage = lifeEffectPercentage != null ? lifeEffectPercentage : 0;
        this.loadEffectPercentage = loadEffectPercentage != null ? loadEffectPercentage : 0;
        this.otherEffects = otherEffects != null ? otherEffects : "";
        this.isEquipped = isEquipped != null ? isEquipped : false;
    }
}
