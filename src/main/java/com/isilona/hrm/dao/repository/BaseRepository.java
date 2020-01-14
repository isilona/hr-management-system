package com.isilona.hrm.dao.repository;

import com.isilona.hrm.dao.entity.AbstractBaseEntity;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<E extends AbstractBaseEntity, Q extends EntityPathBase<E>> extends JpaRepository<E, Long>, QuerydslPredicateExecutor<E>, QuerydslBinderCustomizer<Q> {
    Optional<E> findByUuid(UUID uuid);

    Long deleteByUuid(UUID uuid);

    default void customize(final QuerydslBindings bindings, final Q root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::eq);
    }
}
