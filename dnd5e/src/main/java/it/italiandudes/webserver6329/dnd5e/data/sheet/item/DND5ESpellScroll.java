package it.italiandudes.webserver6329.dnd5e.data.sheet.item;

import it.italiandudes.webserver6329.core.data.MimeImage;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EItemCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ERarity;
import it.italiandudes.webserver6329.dnd5e.data.sheet.misc.DND5ESpell;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_spell_scrolls")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESpellScroll extends DND5EItem {

    // SpellScroll Data
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "spell_id", nullable = false) private DND5ESpell spell;
    @Column(name = "is_usable", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE") private boolean isUsable = true;
    @Column(name = "are_components_required", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE") private boolean areComponentsRequired = false;
    @Column(name = "is_learnable", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE") private boolean isLearnable = true;
    @Column(name = "is_copyable", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE") private boolean isCopyable = true;

    // Constructors
    public DND5ESpellScroll(
            String name, MimeImage itemImage, DND5ERarity rarity, Double weight, Integer quantity, Integer costMR, String description,
            DND5ESpell spell, Boolean isUsable, Boolean areComponentsRequired, Boolean isLearnable, Boolean isCopyable
    ) {
        super(name, DND5EItemCategory.SPELL_SCROLL, itemImage, rarity, weight, quantity, costMR, description);
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.spell = Objects.requireNonNull(spell);
        this.isUsable = isUsable != null ? isUsable : true;
        this.areComponentsRequired = areComponentsRequired != null ? areComponentsRequired : false;
        this.isLearnable = isLearnable != null ? isLearnable : true;
        this.isCopyable = isCopyable != null ? isCopyable : true;
    }
}
