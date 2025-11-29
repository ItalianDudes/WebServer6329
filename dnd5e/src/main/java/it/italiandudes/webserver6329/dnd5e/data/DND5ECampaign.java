package it.italiandudes.webserver6329.dnd5e.data;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.data.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_campaigns")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@Check(constraints = "max_players >= 2")
@SuppressWarnings("unused")
public class DND5ECampaign extends AuditableEntity {

    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "dungeon_master_id", nullable = false) private User dungeonMaster;
    @Column(name = "name", nullable = false) private String name = "";
    @Column(name = "max_players", columnDefinition = "INT DEFAULT 2", nullable = false) @Min(2) private int maxPlayers = 2; // DM Included
    @Column(name = "description", nullable = false) private String description = "";

    // Builder Constructor
    @Builder
    public DND5ECampaign(User dungeonMaster, String name, Integer maxPlayers, String description) {
        this.dungeonMaster = Objects.requireNonNull(dungeonMaster);
        this.name = name != null ? name : "";
        this.maxPlayers = maxPlayers != null ? maxPlayers : 2;
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
        DND5ECampaign campaign = (DND5ECampaign) o;
        return getId() != null && Objects.equals(getId(), campaign.getId());
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
