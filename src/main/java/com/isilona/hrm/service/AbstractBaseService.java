package com.isilona.hrm.service;

import com.isilona.hrm.dao.entity.AbstractBaseEntity;
import com.isilona.hrm.dao.repository.BaseRepository;
import com.isilona.hrm.dto.BaseDto;
import com.isilona.hrm.exception.ResourceNotFoundException;
import com.isilona.hrm.mapping.BaseMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
public abstract class AbstractBaseService<R extends BaseRepository<E, Q>, M extends BaseMapper<D, E>, E extends AbstractBaseEntity, Q extends EntityPathBase<E>, D extends BaseDto> {

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

    @Transactional
    public Long deleteBatch(List<UUID> uuidList) {
        Long deletedRows = repository.deleteByUuidIn(uuidList);
        if (deletedRows == 0) {
            throw new ResourceNotFoundException(uuidList);
        }
        return deletedRows;
    }

    public List<D> findFiltered(Predicate predicate, Sort sort) {
        final BooleanBuilder builder = new BooleanBuilder();

        return StreamSupport.stream(getRepository().findAll(builder.and(predicate), sort).spliterator(), false)
                .map(entity -> mapper.entityToDto(entity))
                .collect(Collectors.toList());
    }
}
