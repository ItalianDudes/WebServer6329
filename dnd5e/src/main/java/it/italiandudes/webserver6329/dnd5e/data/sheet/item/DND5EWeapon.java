package it.italiandudes.webserver6329.dnd5e.data.sheet.item;

import it.italiandudes.webserver6329.core.data.MimeImage;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EEquipmentCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ERarity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dnd5e_weapons")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EWeapon extends DND5EEquipment {

    // Armor Data
    @Column(name = "weapon_category", nullable = false) private String weaponCategory = "";
    @Column(name = "properties", nullable = false) private String properties = "";

    // Constructors
    public DND5EWeapon(
            String name, MimeImage itemImage, DND5ERarity rarity, Double weight, Integer quantity, Integer costMR, String description,
            Integer caEffect, Integer lifeEffect, Integer loadEffect, Double lifeEffectPercentage, Double loadEffectPercentage, String otherEffects, Boolean isEquipped,
            String weaponCategory, String properties
    ) {
        super(name, itemImage, rarity, weight, quantity, costMR, description, DND5EEquipmentCategory.WEAPON, caEffect, lifeEffect, loadEffect, lifeEffectPercentage, loadEffectPercentage, otherEffects, isEquipped);
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.weaponCategory = weaponCategory != null ? weaponCategory : "";
        this.properties = properties != null ? properties : "";
    }
}
