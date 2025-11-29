package it.italiandudes.webserver6329.web.controller;

import it.italiandudes.webserver6329.core.data.VerificationToken;
import it.italiandudes.webserver6329.core.security.enums.VerificationTokenType;
import it.italiandudes.webserver6329.core.security.repository.VerificationTokenRepository;
import it.italiandudes.webserver6329.core.security.service.MailService;
import jakarta.mail.MessagingException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Controller
public final class ControllerMailVerify {

    // Attributes
    @NotNull private final VerificationTokenRepository verificationTokenRepository;
    @NotNull private final MailService mailService;

    // Constructors
    public ControllerMailVerify(@NotNull final VerificationTokenRepository verificationTokenRepository, @NotNull final MailService mailService) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
    }

    // Methods
    @GetMapping("/mail-verify")
    public String verifyMail(@ModelAttribute("token") String token) throws MessagingException {
        Optional<VerificationToken> optVerificationToken = verificationTokenRepository.findByToken(token);
        if (optVerificationToken.isPresent()) {
            VerificationToken verificationToken = optVerificationToken.get();
            if (verificationToken.getType() != VerificationTokenType.EMAIL_VERIFICATION) return "redirect:/";
            else {
                verificationToken.setVerified(true);
                verificationTokenRepository.save(verificationToken);
                mailService.sendConfirmationVerificationMail(verificationToken.getUser().getMail());
                return "redirect:/profile";
            }
        } else return "redirect:/";
    }
}
