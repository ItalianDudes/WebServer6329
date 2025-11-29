package it.italiandudes.webserver6329.core.data;

import it.italiandudes.webserver6329.core.security.enums.UserRole;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @param username Attributes
 */
public record UserDTO(@NotNull String username, @NotNull String mail, @Nullable MimeImage userImage, @NotNull UserRole role) {

    // Constants
    public static final String DEFAULT_USER_PROFILE = "/web/default-profile-image.svg";

    // Constructors
    @Builder public UserDTO {}

    // From User
    @NotNull
    public static UserDTO fromUser(@NotNull final User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .mail(user.getMail())
                .userImage(user.getUserImage())
                .role(user.getRole())
                .build();
    }

    // Methods
    @NotNull
    public String getUserImageOrDefault() {
        return userImage != null ? userImage.toString() : DEFAULT_USER_PROFILE;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserDTO(String username1, String mail1, MimeImage image, UserRole role1))) return false;
        return username().equals(username1) && mail().equals(mail1) && Objects.equals(userImage(), image) && role() == role1;
    }
    @Override
    public int hashCode() {
        int result = username().hashCode();
        result = 31 * result + mail().hashCode();
        result = 31 * result + Objects.hashCode(userImage());
        result = 31 * result + role().hashCode();
        return result;
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return username;
    }
}
