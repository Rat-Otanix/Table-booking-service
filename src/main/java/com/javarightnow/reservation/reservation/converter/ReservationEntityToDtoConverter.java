package com.javarightnow.reservation.reservation.converter;

import com.javarightnow.reservation.converter.BaseEntityToDtoConverter;
import com.javarightnow.reservation.reservation.ReservationDto;
import com.javarightnow.reservation.reservation.ReservationEntity;
import org.springframework.stereotype.Component;

/**
 * @author hadi
 */
@Component
public final class ReservationEntityToDtoConverter
        extends BaseEntityToDtoConverter<ReservationEntity, ReservationDto> {

    @Override
    protected ReservationDto doAudit(final ReservationEntity entity) {

        return ReservationDto.builder()
                .customer(entity.getCustomer().getId())
                .table(entity.getTable().getId())
                .timesLot(entity.getTimesLot())
                .build();
    }

}