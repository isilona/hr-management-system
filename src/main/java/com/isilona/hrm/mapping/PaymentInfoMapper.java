package com.isilona.hrm.mapping;

import com.isilona.hrm.dao.entity.PaymentInfo;
import com.isilona.hrm.dto.PaymentInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentInfoMapper extends BaseMapper<PaymentInfoDto, PaymentInfo> {
}
