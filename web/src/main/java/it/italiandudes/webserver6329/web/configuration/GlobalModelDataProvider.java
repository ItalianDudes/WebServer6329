package it.italiandudes.webserver6329.web.configuration;

import it.italiandudes.webserver6329.core.data.User;
import it.italiandudes.webserver6329.core.data.UserDTO;
import it.italiandudes.webserver6329.core.security.WebServer6329UserDetails;
import it.italiandudes.webserver6329.core.security.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public final class GlobalModelDataProvider {

    // Attributes
    @NotNull private final UserRepository userRepository;

    // Constructors
    public GlobalModelDataProvider(@NotNull final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("loggedUserDTO")
    public UserDTO addUserToModel(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            WebServer6329UserDetails userDetails = (WebServer6329UserDetails) authentication.getPrincipal();
            Optional<User> optLoggedUser = userRepository.findById(userDetails.getUser().getId());
            return optLoggedUser.map(UserDTO::fromUser).orElse(null);
        }
        return null;
    }
}
