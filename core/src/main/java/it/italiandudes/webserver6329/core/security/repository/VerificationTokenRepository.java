package it.italiandudes.webserver6329.core.security.repository;

import it.italiandudes.webserver6329.core.data.User;
import it.italiandudes.webserver6329.core.data.VerificationToken;
import it.italiandudes.webserver6329.core.security.enums.VerificationTokenType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    // FindBy
    @NotNull Optional<VerificationToken> findByToken(String token);
    @NotNull Optional<VerificationToken> findByUserAndType(User user, VerificationTokenType type);
    @NotNull List<VerificationToken> findByIsVerifiedFalseAndExpiryDateBefore(LocalDateTime now);

}
