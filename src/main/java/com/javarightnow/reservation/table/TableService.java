package com.javarightnow.reservation.table;

import com.javarightnow.reservation.service.IGenericService;
import com.javarightnow.reservation.table.specificfind.TableReservationCustomerDto;
import com.javarightnow.reservation.dataobject.dto.SimplePageRequestDTO;
import com.javarightnow.reservation.reservation.ReservationDto;
import com.javarightnow.reservation.exception.GeneralException;

public interface TableService extends IGenericService<TableDto, Long> {

    void makeReservation(ReservationDto reservationDto) throws GeneralException;

    TableReservationCustomerDto findReservationsByTableId(Long tableId, SimplePageRequestDTO simplePageRequestDTO) throws GeneralException;

    void checkExists(String name) throws GeneralException;

}