package it.italiandudes.webserver6329.core.security.filter;

import it.italiandudes.webserver6329.core.security.WebServer6329UserDetails;
import it.italiandudes.webserver6329.core.security.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public final class UserExistenceFilter extends OncePerRequestFilter {

    // Attributes
    @NotNull private final UserRepository userRepository;

    // Constructors
    public UserExistenceFilter(@NotNull final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Methods
    @Override
    public void doFilterInternal(@NotNull final HttpServletRequest request, @NotNull final HttpServletResponse response, @NotNull final FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof WebServer6329UserDetails userDetails) {
            String email = userDetails.getUsername();
            boolean exists = userRepository.existsByMail(email.trim().toLowerCase());
            if (!exists) {
                request.getSession().invalidate();
                SecurityContextHolder.clearContext();
                response.sendRedirect("/login?accountDeleted");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
