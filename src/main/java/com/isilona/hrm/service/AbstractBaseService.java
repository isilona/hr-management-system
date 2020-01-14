package com.isilona.hrm.service;

import com.isilona.hrm.dao.entity.AbstractBaseEntity;
import com.isilona.hrm.dao.repository.BaseRepository;
import com.isilona.hrm.dto.BaseDto;
import com.isilona.hrm.exception.ResourceNotFoundException;
import com.isilona.hrm.mapping.BaseMapper;
import lombok.Getter;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public abstract class AbstractBaseService<R extends BaseRepository<E>, M extends BaseMapper<D, E>, E extends AbstractBaseEntity, D extends BaseDto> {

    private R repository;
    private M mapper;

    public AbstractBaseService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public D create(D input) {
        E entity = mapper.dtoToEntity(input);
        E savedEntity = repository.save(entity);
        return mapper.entityToDto(savedEntity);
    }

    public D read(UUID uuid) {
        Optional<E> entity = repository.findByUuid(uuid);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(uuid);
        }
        return mapper.entityToDto(entity.get());
    }

    public List<D> readAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.entityToDto(entity))
                .collect(Collectors.toList());
    }

    public abstract D update(D input);


    @Transactional
    public Long delete(UUID uuid) {
        Long deletedRows = repository.deleteByUuid(uuid);
        if (deletedRows == 0) {
            throw new ResourceNotFoundException(uuid);
        }
        return deletedRows;
    }
}
