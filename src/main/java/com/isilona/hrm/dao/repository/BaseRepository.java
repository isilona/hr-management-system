package com.isilona.hrm.dao.repository;

import com.isilona.hrm.dao.entity.AbstractBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<E extends AbstractBaseEntity> extends JpaRepository<E, Long> {
    Optional<E> findByUuid(UUID uuid);

    Long deleteByUuid(UUID uuid);
}
