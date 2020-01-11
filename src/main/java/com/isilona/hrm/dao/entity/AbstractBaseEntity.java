package com.isilona.hrm.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractBaseEntity implements Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @Column(unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

    @Column
    private Boolean isActive;

    public boolean isNew() {
        return this.uuid == null && this.id == null;
    }

    @PrePersist
    private void init() {
        this.uuid = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }

    @PreUpdate
    private void update() {
        this.updatedAt = LocalDateTime.now();
    }

    public void updateStatus(Boolean newStatus) {
        if (Boolean.TRUE.equals(newStatus)) {
            // activate (undelete)
            this.setIsActive(true);
            this.setDeletedAt(null);
        }
        if (Boolean.FALSE.equals(newStatus)) {
            // deactivate (delete)
            this.setIsActive(false);
            this.setDeletedAt(LocalDateTime.now());
        }
    }

    public boolean notDeleted() {
        return isNull(this.deletedAt) && this.isActive;
    }

    public boolean isDeleted() {
        return !notDeleted();
    }
}
