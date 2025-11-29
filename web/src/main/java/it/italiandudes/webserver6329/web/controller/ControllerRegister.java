package it.italiandudes.webserver6329.web.controller;

import it.italiandudes.webserver6329.core.security.dto.RegisterDTO;
import it.italiandudes.webserver6329.core.security.service.WebServer6329UserDetailsService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public final class ControllerRegister {

    // Attributes
    @NotNull private final AuthenticationManager authenticationManager;
    @NotNull private final WebServer6329UserDetailsService userDetailsService;
    @NotNull private final RequestCache requestCache;

    // Constructors
    public ControllerRegister(@NotNull final AuthenticationManager authenticationManager, @NotNull final WebServer6329UserDetailsService userDetailsService, @NotNull final RequestCache requestCache) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.requestCache = requestCache;
    }

    // Methods
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "web/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerDTO") RegisterDTO dto, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "web/register";
        }

        if (!dto.getConfirmPlainPassword().equals(dto.getPlainPassword())) {
            result.reject("password-mismatch", "La conferma della password deve essere uguale alla password");
            return "web/register";
        }

        dto.setMail(dto.getMail().trim().toLowerCase()); // Mail Trim And Lowercasing
        if (dto.getUsername().trim().toLowerCase().equalsIgnoreCase(dto.getMail())) {
            result.reject("username-equals-mail", "Lo username non deve essere uguale alla mail.");
            return "web/register";
        }

        if (userDetailsService.register(dto)) { // Successful register?
            try { // Auto-Login Attempt
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getMail(), dto.getPlainPassword());
                Authentication authentication = authenticationManager.authenticate(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                HttpSession session = request.getSession(true);
                session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
                SavedRequest savedRequest = requestCache.getRequest(request, response);
                if (savedRequest != null) {
                    return "redirect:" + savedRequest.getRedirectUrl();
                }
                else return "redirect:/";
            } catch (Exception e) {
                return "redirect:/login";
            }
        } else {
            result.reject("duplicated-credentials", "Email o username già usati");
            return "web/register";
        }
    }
}
