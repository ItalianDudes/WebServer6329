package it.italiandudes.webserver6329.core.security.repository;

import it.italiandudes.webserver6329.core.data.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // FindBy
    @NotNull Optional<User> findByMail(String mail);
    @NotNull Optional<User> findByUsername(String username);

    boolean existsByMail(String mail);
}
