package it.italiandudes.webserver6329.core.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class RegisterDTO {

    // Attributes
    @NotNull @NotBlank(message = "Username obbligatorio") @Size(min = 4, message = "Lo username deve essere contenere almeno 4 caratteri") private String username;
    @NotNull @NotBlank(message = "Email obbligatoria") @Email(message = "Formato email non valido") private String mail;
    @NotNull @NotBlank(message = "Password obbligatoria") @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "La password deve contenere di almeno 8 caratteri, almeno una maiuscola, una minuscola, un numero e un simbolo"
    ) private String plainPassword;
    @NotNull @NotBlank(message = "Conferma Password obbligatoria") @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "La conferma della password deve contenere di almeno 8 caratteri, almeno una maiuscola, una minuscola, un numero e un simbolo e deve essere uguale alla password"
    ) private String confirmPlainPassword;
}
