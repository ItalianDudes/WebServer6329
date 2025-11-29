package it.italiandudes.webserver6329.dnd5e.data.sheet.tabs;

import it.italiandudes.webserver6329.core.audit.AuditableEntity;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webserver6329.dnd5e.data.sheet.enums.DND5EMainAbilities;
import it.italiandudes.webserver6329.dnd5e.data.sheet.misc.DND5ESpell;
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
@Table(name = "dnd5e_sheet_tabs_spells")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabSpells extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Reference
    @OneToOne(mappedBy = "tabSpells", optional = false) private DND5ESheet sheet;

    // Header
    @Column(name = "caster_ability", nullable = false, columnDefinition = "VARCHAR(32) DEFAULT 'INTELLIGENCE'") @Enumerated(EnumType.STRING) private DND5EMainAbilities casterAbility;
    @Transient private int spellST = 0;
    @Transient private int spellAttackBonus = 0;

    // Learned Spells
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id", nullable = false) private List<DND5ESpell> learnedSpells = new ArrayList<>();

    // Slots
    @Transient private final List<DND5ESpell> lvl0Spells = new ArrayList<>();
    @Transient private final List<DND5ESpell> lvl1PreparedSpells = new ArrayList<>();
    @Column(name = "lvl1_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl1UsedSlots;
    @Column(name = "lvl1_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl1TotalSlots;

    @Transient private final List<DND5ESpell> lvl2PreparedSpells = new ArrayList<>();
    @Column(name = "lvl2_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl2UsedSlots;
    @Column(name = "lvl2_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl2TotalSlots;

    @Transient private final List<DND5ESpell> lvl3PreparedSpells = new ArrayList<>();
    @Column(name = "lvl3_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl3UsedSlots;
    @Column(name = "lvl3_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl3TotalSlots;

    @Transient private final List<DND5ESpell> lvl4PreparedSpells = new ArrayList<>();
    @Column(name = "lvl4_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl4UsedSlots;
    @Column(name = "lvl4_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl4TotalSlots;

    @Transient private final List<DND5ESpell> lvl5PreparedSpells = new ArrayList<>();
    @Column(name = "lvl5_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl5UsedSlots;
    @Column(name = "lvl5_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl5TotalSlots;

    @Transient private final List<DND5ESpell> lvl6PreparedSpells = new ArrayList<>();
    @Column(name = "lvl6_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl6UsedSlots;
    @Column(name = "lvl6_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl6TotalSlots;

    @Transient private final List<DND5ESpell> lvl7PreparedSpells = new ArrayList<>();
    @Column(name = "lvl7_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl7UsedSlots;
    @Column(name = "lvl7_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl7TotalSlots;

    @Transient private final List<DND5ESpell> lvl8PreparedSpells = new ArrayList<>();
    @Column(name = "lvl8_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl8UsedSlots;
    @Column(name = "lvl8_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl8TotalSlots;

    @Transient private final List<DND5ESpell> lvl9PreparedSpells = new ArrayList<>();
    @Column(name = "lvl9_used_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl9UsedSlots;
    @Column(name = "lvl9_total_slots", nullable = false, columnDefinition = "INT DEFAULT 0") private int lvl9TotalSlots;

    // Constructors
    @Builder
    public DND5ESheetTabSpells(DND5EMainAbilities casterAbility, List<DND5ESpell> learnedSpells) {
        WebServer6329Logger.getLogger().debug(this.getClass().getName());
        this.casterAbility = casterAbility != null ? casterAbility : DND5EMainAbilities.INTELLIGENCE;
        this.learnedSpells = learnedSpells != null ? learnedSpells : new ArrayList<>();
        updatePreparedSpells();
    }

    // Methods
    public void updatePreparedSpells() {
        lvl0Spells.clear();
        lvl1PreparedSpells.clear();
        lvl2PreparedSpells.clear();
        lvl3PreparedSpells.clear();
        lvl4PreparedSpells.clear();
        lvl5PreparedSpells.clear();
        lvl6PreparedSpells.clear();
        lvl7PreparedSpells.clear();
        lvl8PreparedSpells.clear();
        lvl9PreparedSpells.clear();
        if (learnedSpells != null && !learnedSpells.isEmpty()) {
            lvl0Spells.addAll(learnedSpells.stream().filter(spell -> spell.getSpellLevel() == 0).toList());
            lvl1PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 1).toList());
            lvl2PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 2).toList());
            lvl3PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 3).toList());
            lvl4PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 4).toList());
            lvl5PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 5).toList());
            lvl6PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 6).toList());
            lvl7PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 7).toList());
            lvl8PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 8).toList());
            lvl9PreparedSpells.addAll(learnedSpells.stream().filter(DND5ESpell::isPrepared).filter(spell -> spell.getSpellLevel() == 9).toList());
        }
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabSpells that = (DND5ESheetTabSpells) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
