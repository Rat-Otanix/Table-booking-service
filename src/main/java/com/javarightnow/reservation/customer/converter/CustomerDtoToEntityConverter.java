package com.javarightnow.reservation.customer.converter;

import com.javarightnow.reservation.converter.BaseDtoToEntityConverter;
import com.javarightnow.reservation.customer.CustomerDto;
import com.javarightnow.reservation.customer.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 * @author hadi
 */
@Component
public final class CustomerDtoToEntityConverter
        extends BaseDtoToEntityConverter<CustomerDto, CustomerEntity> {

    @Override
    public CustomerEntity doAudit(final CustomerDto dto) {

        final CustomerEntity entity = CustomerEntity.builder()
                .name(dto.getCustomer_name())
                .build();

        return entity;

    }
}
