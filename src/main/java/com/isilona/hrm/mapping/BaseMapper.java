package com.isilona.hrm.mapping;

import com.isilona.hrm.dao.entity.AbstractBaseEntity;
import com.isilona.hrm.dto.BaseDto;

public interface BaseMapper<D extends BaseDto, E extends AbstractBaseEntity> {

    D entityToDto(E entity);

    E dtoToEntity(D dto);


}
