package it.italiandudes.webserver6329.web.controller;

import it.italiandudes.webserver6329.core.data.User;
import it.italiandudes.webserver6329.core.data.VerificationToken;
import it.italiandudes.webserver6329.core.security.WebServer6329UserDetails;
import it.italiandudes.webserver6329.core.security.dto.UserDataEditorDTO;
import it.italiandudes.webserver6329.core.security.enums.VerificationTokenType;
import it.italiandudes.webserver6329.core.security.repository.UserRepository;
import it.italiandudes.webserver6329.core.security.repository.VerificationTokenRepository;
import it.italiandudes.webserver6329.core.security.service.WebServer6329UserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public final class ControllerWebProfile {

    // Attributes
    @NotNull private final WebServer6329UserDetailsService userDetailsService;
    @NotNull private final VerificationTokenRepository verificationTokenRepository;
    @NotNull private final UserRepository userRepository;

    // Constructors
    public ControllerWebProfile(@NotNull final WebServer6329UserDetailsService userDetailsService, @NotNull final VerificationTokenRepository verificationTokenRepository, @NotNull final UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    // Profile Methods
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal WebServer6329UserDetails userDetails, Model model) {
        assert userDetails != null; // Page protected
        Optional<User> optUser = userRepository.findById(userDetails.getUser().getId());
        assert optUser.isPresent();
        User loggedUser = optUser.get();
        model.addAttribute("user", loggedUser);
        Optional<VerificationToken> optVerificationToken = verificationTokenRepository.findByUserAndType(loggedUser, VerificationTokenType.EMAIL_VERIFICATION);
        if (optVerificationToken.isPresent()) {
            VerificationToken verificationToken = optVerificationToken.get();
            model.addAttribute("mailVerified", verificationToken.isVerified());
        } else model.addAttribute("mailVerified", false);
        return "web/profile";
    }

    // Profile Edit Methods
    @GetMapping("/profile/edit")
    public String showProfileEditor(@AuthenticationPrincipal WebServer6329UserDetails userDetails, Model model) {
        assert userDetails != null; // Page Protected
        model.addAttribute("editorDTO", new UserDataEditorDTO());
        return "web/profile_editor";
    }
    @PostMapping("/profile/edit")
    public String saveProfileEditor(@AuthenticationPrincipal WebServer6329UserDetails userDetails, @Valid @ModelAttribute("editorDTO") UserDataEditorDTO dto, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
        assert userDetails != null; // Page Protected
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "web/profile_editor";
        }
        if (
                (dto.getUsername() == null || dto.getUsername().isBlank()) &&
                        (dto.getPlainPassword() == null || dto.getPlainPassword().isBlank())
        ) return "redirect:/profile";
        if (dto.getPlainPassword() != null && !dto.getPlainPassword().isBlank() && !dto.getPlainPassword().equals(dto.getPlainConfirmPassword())) {
            model.addAttribute("errors", "Le due password non coincidono.");
            return "web/profile_editor";
        }
        switch (userDetailsService.updateUserData(dto, userDetails.getUser().getMail())) {
            case SAVE_ERROR -> {
                model.addAttribute("errors", "Si è verificato un errore durante l'aggiornamento dei dati.");
                return "web/profile_editor";
            }
            case LOGOUT -> {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                    return "redirect:/";
                } else return "redirect:/profile";
            }
            default -> { // SUCCESS
                return "redirect:/profile";
            }
        }
    }
}
