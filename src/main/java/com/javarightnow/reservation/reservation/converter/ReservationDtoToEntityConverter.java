package com.javarightnow.reservation.reservation.converter;

import com.javarightnow.reservation.table.TableEntity;
import com.javarightnow.reservation.converter.BaseDtoToEntityConverter;
import com.javarightnow.reservation.reservation.ReservationDto;
import com.javarightnow.reservation.customer.CustomerEntity;
import com.javarightnow.reservation.reservation.ReservationEntity;
import org.springframework.stereotype.Component;

/**
 * @author hadi
 */
@Component
public final class ReservationDtoToEntityConverter
        extends BaseDtoToEntityConverter<ReservationDto, ReservationEntity> {

    @Override
    public ReservationEntity doAudit(final ReservationDto dto) {

        final ReservationEntity entity = ReservationEntity.builder()
                .customer(dto.getCustomer() != null ? new CustomerEntity(dto.getCustomer()) : null)
                .table(dto.getTable() != null ? new TableEntity(dto.getTable()) : null)
                .timesLot(dto.getTimesLot())
                .build();

        return entity;
    }
}
