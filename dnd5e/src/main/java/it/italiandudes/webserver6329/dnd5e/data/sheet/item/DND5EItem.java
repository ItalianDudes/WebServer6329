package it.italiandudes.webserver6329.dnd5e.data.sheet.item;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.data.MimeImage;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EItemCategory;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5ERarity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_items")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor // Needed for JPA
public class DND5EItem extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Item Data
    @Column(name = "name", nullable = false) private String name = "";
    @Column(name = "item_category", nullable = false) @Enumerated(EnumType.STRING) private DND5EItemCategory itemCategory;
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "item_image_id") private MimeImage itemImage = null;
    @Column(name = "rarity", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'COMMON'") @Enumerated(EnumType.STRING) private DND5ERarity rarity = DND5ERarity.COMMON;
    @Column(name = "weight", nullable = false, columnDefinition = "INT DEFAULT 0") private double weight = 0;
    @Column(name = "quantity", nullable = false, columnDefinition = "INT DEFAULT 0") private int quantity = 0;
    @Column(name = "cost_mr", nullable = false, columnDefinition = "INT DEFAULT 0") private int costMR = 0;
    @Column(name = "description", nullable = false) private String description = "";

    // Constructors
    public DND5EItem(String name, DND5EItemCategory itemCategory, MimeImage itemImage, DND5ERarity rarity, Double weight, Integer quantity, Integer costMR, String description) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.name = name != null ? name : "";
        this.itemCategory = Objects.requireNonNull(itemCategory);
        this.itemImage = itemImage;
        this.rarity = rarity != null ? rarity : DND5ERarity.COMMON;
        this.weight = weight != null ? weight : 0;
        this.quantity = quantity != null ? quantity : 0;
        this.costMR = costMR != null ? costMR : 0;
        this.description = description != null ? description : "";
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5EItem dnd5EItem = (DND5EItem) o;
        return getId() != null && Objects.equals(getId(), dnd5EItem.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return name;
    }
}
