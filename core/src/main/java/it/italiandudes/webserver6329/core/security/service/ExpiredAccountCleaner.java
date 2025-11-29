package it.italiandudes.webserver6329.core.security.service;

import it.italiandudes.webserver6329.core.data.User;
import it.italiandudes.webserver6329.core.data.VerificationToken;
import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.core.security.WebServer6329UserDetails;
import it.italiandudes.webserver6329.core.security.enums.VerificationTokenType;
import it.italiandudes.webserver6329.core.security.repository.UserRepository;
import it.italiandudes.webserver6329.core.security.repository.VerificationTokenRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public final class ExpiredAccountCleaner {

    // Attributes
    @NotNull private final SessionRegistry sessionRegistry;
    @NotNull private final VerificationTokenRepository verificationTokenRepository;
    @NotNull private final UserRepository userRepository;

    // Constructors
    public ExpiredAccountCleaner(@NotNull final SessionRegistry sessionRegistry, @NotNull final VerificationTokenRepository verificationTokenRepository, @NotNull final UserRepository userRepository) {
        this.sessionRegistry = sessionRegistry;
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    // Methods
    @Scheduled(cron = "0 0 3 * * *") // Every day at 3:00
    public void cleanExpiredUnverifiedAccounts() {
        List<VerificationToken> expiredTokens = verificationTokenRepository.findByIsVerifiedFalseAndExpiryDateBefore(LocalDateTime.now())
                .stream().filter(verificationToken -> verificationToken.getType() == VerificationTokenType.EMAIL_VERIFICATION).toList();
        for (VerificationToken token : expiredTokens) {
            User user = token.getUser();
            invalidateUserSession(user);
            verificationTokenRepository.delete(token);
            userRepository.delete(user);
            WebServer6329Logger.getLogger().info("{}\" because of email verification expired.", "Deleted account \"" + user.getUsername());
        }
        WebServer6329Logger.getLogger().info("Total deleted account: {}", expiredTokens.size());
    }
    private void invalidateUserSession(@NotNull final User user) {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (Object principal : principals) {
            if (principal instanceof WebServer6329UserDetails userDetails && userDetails.getUser().getMail().equals(user.getMail())) {
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
                for (SessionInformation session : sessions) {
                    session.expireNow();
                }
            }
        }
    }
}
