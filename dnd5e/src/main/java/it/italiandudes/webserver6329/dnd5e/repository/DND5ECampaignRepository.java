package it.italiandudes.webserver6329.dnd5e.repository;

import it.italiandudes.webserver6329.core.data.User;
import it.italiandudes.webserver6329.dnd5e.data.DND5ECampaign;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DND5ECampaignRepository extends JpaRepository<DND5ECampaign, Long> {

    // Find By
    @NotNull Optional<DND5ECampaign> findById(@NotNull Long id);

    // Find All By
    @NotNull List<@NotNull DND5ECampaign> findAllByDungeonMasterAndName(@NotNull User user, @NotNull String name);
    @NotNull List<@NotNull DND5ECampaign> findAllByDungeonMaster(@NotNull User user);
    @NotNull List<@NotNull DND5ECampaign> findAllByName(@NotNull String name);
}
