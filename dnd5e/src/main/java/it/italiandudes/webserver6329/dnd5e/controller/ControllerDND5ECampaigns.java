package it.italiandudes.webserver6329.dnd5e.controller;

import it.italiandudes.webserver6329.core.security.repository.UserRepository;
import it.italiandudes.webserver6329.dnd5e.data.DND5ECampaign;
import it.italiandudes.webserver6329.dnd5e.repository.DND5ECampaignRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public final class ControllerDND5ECampaigns {

    // Repositories
    @NotNull private final DND5ECampaignRepository campaignRepository;
    private final UserRepository userRepository;

    // Constructors
    public ControllerDND5ECampaigns(@NotNull final DND5ECampaignRepository campaignRepository, UserRepository userRepository) {
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
    }

    // Mappings
    @GetMapping("/dnd5e/campaigns")
    private String campaigns(Model model) {
        if (campaignRepository.count() == 0) {
            DND5ECampaign campaign = new DND5ECampaign(userRepository.findById(1L).get(), "Tester", 5, "Tester Desc");
            campaignRepository.save(campaign);
        }
        model.addAttribute("campaigns", campaignRepository.findAll());
        return "dnd5e/campaigns-list";
    }

    @GetMapping("/dnd5e/campaigns/{campaign-id}")
    private String selectedCampaign(@PathVariable(name = "campaign-id") Long id, Model model) {
        Optional<DND5ECampaign> optCampaign = campaignRepository.findById(id);
        optCampaign.ifPresent(campaign -> model.addAttribute("campaign", campaign));
        return "dnd5e/campaign";
    }
}
