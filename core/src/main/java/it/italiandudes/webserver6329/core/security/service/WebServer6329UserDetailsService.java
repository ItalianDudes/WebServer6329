package it.italiandudes.webserver6329.core.security.service;

import it.italiandudes.webserver6329.core.data.User;
import it.italiandudes.webserver6329.core.data.VerificationToken;
import it.italiandudes.webserver6329.core.security.WebServer6329UserDetails;
import it.italiandudes.webserver6329.core.security.dto.RegisterDTO;
import it.italiandudes.webserver6329.core.security.dto.UserDataEditorDTO;
import it.italiandudes.webserver6329.core.security.enums.UserDataUpdateResult;
import it.italiandudes.webserver6329.core.security.enums.VerificationTokenType;
import it.italiandudes.webserver6329.core.security.repository.UserRepository;
import it.italiandudes.webserver6329.core.security.repository.VerificationTokenRepository;
import jakarta.mail.MessagingException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class WebServer6329UserDetailsService implements UserDetailsService {

    // Attributes
    @NotNull private final UserRepository userRepository;
    @NotNull private final VerificationTokenRepository verificationTokenRepository;
    @NotNull private final PasswordEncoder passwordEncoder;
    @NotNull private final MailService mailService;

    // Constructor
    public WebServer6329UserDetailsService(@NotNull final UserRepository userRepository, @NotNull final VerificationTokenRepository verificationTokenRepository, @NotNull final PasswordEncoder passwordEncoder, @NotNull final MailService mailService) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    // Methods
    @Override
    public UserDetails loadUserByUsername(@NotNull final String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail.trim().toLowerCase()).orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        return new WebServer6329UserDetails(user);
    }
    public boolean register(@NotNull RegisterDTO dto) throws MessagingException {
        if (userRepository.findByMail(dto.getMail()).isPresent()) return false;
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) return false;
        User newUser = userRepository.save(User.fromRegisterDTO(dto, passwordEncoder));
        String token = MailService.generateMailVerificationToken();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(newUser)
                .type(VerificationTokenType.EMAIL_VERIFICATION)
                .build();
        verificationTokenRepository.save(verificationToken);
        mailService.sendVerificationMail(dto.getMail(), token);
        return true;
    }
    @NotNull
    public UserDataUpdateResult updateUserData(@NotNull UserDataEditorDTO dto, @NotNull final String mail) {
        Optional<User> optUser = userRepository.findByMail(mail);
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (dto.getUsername() != null && !dto.getUsername().isBlank()) user.setUsername(dto.getUsername());
            boolean passwordChanged = false;
            if (dto.getPlainPassword() != null && !dto.getPlainPassword().isBlank()
            && dto.getPlainConfirmPassword() != null && !dto.getPlainConfirmPassword().isBlank()) {
                user.setPasswordHash(passwordEncoder.encode(dto.getPlainPassword()));
                passwordChanged = true;
            }
            userRepository.save(user);
            WebServer6329UserDetails newUserDetails = new WebServer6329UserDetails(user);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    newUserDetails,
                    newUserDetails.getPassword(),
                    newUserDetails.getAuthorities()
            ));
            if (passwordChanged) return UserDataUpdateResult.LOGOUT;
            else return UserDataUpdateResult.SUCCESS;
        } else return UserDataUpdateResult.SAVE_ERROR;
    }
}
