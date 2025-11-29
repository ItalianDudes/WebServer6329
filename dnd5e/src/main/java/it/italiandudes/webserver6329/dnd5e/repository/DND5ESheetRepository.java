package it.italiandudes.webserver6329.dnd5e.repository;

import it.italiandudes.webserver6329.core.data.User;
import it.italiandudes.webserver6329.dnd5e.data.DND5ECampaign;
import it.italiandudes.webserver6329.dnd5e.data.sheet.DND5ESheet;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DND5ESheetRepository extends JpaRepository<DND5ESheet, Long> {

    // Find By
    @NotNull Optional<DND5ESheet> findById(@NotNull Long id);

    // Find All By
    @NotNull List<@NotNull DND5ESheet> findAllByCampaignAndOwner(@NotNull DND5ECampaign campaign, @NotNull User owner);
    @NotNull List<@NotNull DND5ESheet> findAllByOwnerAndTabCharacter_CharacterName(@NotNull User owner, @NotNull String characterName);
    @NotNull List<@NotNull DND5ESheet> findAllByCampaign(@NotNull DND5ECampaign campaign);
    @NotNull List<@NotNull DND5ESheet> findAllByOwner(@NotNull User owner);
}
