package com.isilona.hrm.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    public boolean isNew() {
        return this.uuid == null && this.id == null;
    }

    @PrePersist
    private void init() {
        this.uuid = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
