package it.italiandudes.webserver6329.core.security.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class UserDataEditorDTO {

    // Attributes
    @Pattern(
            regexp = "^$|^.{4,}$",
            message = "Lo username, se da cambiare, deve essere lungo almeno 4 caratteri."
    ) private String username;
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "La password, se da cambiare, deve contenere di almeno 8 caratteri, almeno una maiuscola, una minuscola, un numero e un simbolo."
    ) private String plainPassword;
    private String plainConfirmPassword;
}
