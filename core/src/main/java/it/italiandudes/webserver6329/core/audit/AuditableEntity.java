package it.italiandudes.webserver6329.core.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass @SuppressWarnings("unused")
public abstract class AuditableEntity {
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP") @CreationTimestamp protected LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP") @UpdateTimestamp protected LocalDateTime updatedAt = LocalDateTime.now();
}
