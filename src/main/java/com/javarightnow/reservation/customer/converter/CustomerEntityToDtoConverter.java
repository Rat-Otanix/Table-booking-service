package com.javarightnow.reservation.customer.converter;

import com.javarightnow.reservation.converter.BaseEntityToDtoConverter;
import com.javarightnow.reservation.customer.CustomerDto;
import com.javarightnow.reservation.customer.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 * @author hadi
 */
@Component
public final class CustomerEntityToDtoConverter
        extends BaseEntityToDtoConverter<CustomerEntity, CustomerDto> {

    @Override
    protected CustomerDto doAudit(final CustomerEntity entity) {

        return CustomerDto.builder()
                .customer_name(entity.getName())
                .build();
    }

}