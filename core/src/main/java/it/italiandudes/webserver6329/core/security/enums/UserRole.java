package it.italiandudes.webserver6329.core.security.enums;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@Getter @SuppressWarnings("unused")
public enum UserRole {
    USER(0, "Utente", 0),
    ADMIN(1, "Amministratore", 1);

    // Attributes
    @NotNull private final String name;
    private final int dbValue;
    private final int permissionLevel;

    // Constructor
    UserRole(int dbValue, @NotNull String name, int permissionLevel) {
        this.dbValue = dbValue;
        this.name = name;
        this.permissionLevel = permissionLevel;
    }

    // static
    @NotNull
    public static Optional<UserRole> fromDbValue(int dbValue) {
        for (UserRole role : UserRole.values()) {
            if (role.dbValue == dbValue) return Optional.of(role);
        }
        return Optional.empty();
    }

    // Methods
    public boolean hasPermissionFor(@NotNull final UserRole role) {
        return this.permissionLevel >= role.permissionLevel;
    }
    @Override @NotNull
    public String toString() {
        return name;
    }
}
