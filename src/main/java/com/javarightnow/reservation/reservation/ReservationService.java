package com.javarightnow.reservation.reservation;

import com.javarightnow.reservation.service.IGenericService;
import com.javarightnow.reservation.exception.GeneralException;
import com.javarightnow.reservation.exception.InvalidInputException;
import com.javarightnow.reservation.util.IDateTime;

import java.util.List;

public interface ReservationService extends IGenericService<ReservationDto, Long> {

    Boolean isTableAvailable(Long tableId, TimesLot timesLot);

    List<ReservationDto> findByTableId(Long tableId);

    /**
     * timesLot.getFrom() must less than timesLot.getTo()
     *
     * @param timesLot
     * @param dateTime
     * @throws GeneralException
     */
    void fromLessThanToDates(TimesLot timesLot, IDateTime dateTime) throws InvalidInputException;
}