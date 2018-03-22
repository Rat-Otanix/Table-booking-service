package com.javarightnow.reservation.service;

import com.javarightnow.reservation.exception.*;
import com.javarightnow.reservation.reservation.TimesLot;
import com.javarightnow.reservation.table.TableService;
import com.javarightnow.reservation.table.specificfind.TableReservationCustomerDto;
import com.javarightnow.reservation.ReservationBaseTest;
import com.javarightnow.reservation.dataobject.dto.SimplePageRequestDTO;
import com.javarightnow.reservation.reservation.ReservationDto;
import com.javarightnow.reservation.table.TableDto;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TableServiceTestReservationTest extends ReservationBaseTest {

    @Autowired
    private TableService tableService;

    @Test(expected = DuplicateResourceException.class)
    public void createTable_duplicateName_throwDuplicateResourceException() throws GeneralException {
        TableDto tableDto = TableDto.builder()
                .name("Table1")
                .build();
        tableService.save(tableDto);
    }

    @Test
    public void createTable_validName_succeed() throws GeneralException {
        TableDto tableDto = TableDto.builder()
                .name("Table4")
                .build();
        TableDto saved = tableService.save(tableDto);
        assertThat(saved.getName(), notNullValue());
        assertThat(saved.getId(), Matchers.greaterThan(3L));
        assertEquals(tableDto.getName(), saved.getName());
    }

    @Test(expected = EmptyInputException.class)
    public void createTable_inValidName_throwEmptyInputException() throws GeneralException {
        TableDto tableDto = TableDto.builder()
                .name(null)
                .build();
        TableDto saved = tableService.save(tableDto);
    }

    @Test
    public void makeReservation_validData_succeed() throws GeneralException {

        LocalDateTime from = LocalDateTime.of(2050, Month.MARCH, 16, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2050, Month.MARCH, 16, 12, 0, 0);

        ReservationDto reservationDto = ReservationDto.builder()
                .customer(2L)
                .table(1L)
                .timesLot(new TimesLot(from, to))
                .build();
        tableService.makeReservation(reservationDto);
    }

    @Test(expected = ReservationConflictException.class)
    public void makeReservation_conflictDateTime_throwConflictException() throws GeneralException {

        LocalDateTime from = LocalDateTime.of(2050, Month.MARCH, 16, 9, 0, 0);
        LocalDateTime to = LocalDateTime.of(2050, Month.MARCH, 16, 9, 30, 0);

        ReservationDto reservationDto = ReservationDto.builder()
                .customer(2L)
                .table(1L)
                .timesLot(new TimesLot(from, to))
                .build();
        tableService.makeReservation(reservationDto);
    }

    @Test(expected = InvalidInputException.class)
    public void makeReservation_fromGreaterThanTo_throwInvalidInputException() throws GeneralException {

        LocalDateTime from = LocalDateTime.of(2050, Month.MARCH, 16, 12, 0, 1);
        LocalDateTime to = LocalDateTime.of(2050, Month.MARCH, 16, 12, 0, 0);

        ReservationDto reservationDto = ReservationDto.builder()
                .customer(2L)
                .table(1L)
                .timesLot(new TimesLot(from, to))
                .build();
        tableService.makeReservation(reservationDto);
    }

    @Test(expected = NoSuchResourceException.class)
    public void findTable_invalidId_throwNoSuchResourceException() throws GeneralException {
        Long tableId = 200L;
        tableService.getById(tableId);
    }

    @Test
    public void findReservationsByTableId_validData_success() throws GeneralException {
        Long tableId = 1L;
        SimplePageRequestDTO simplePageRequestDTO = new SimplePageRequestDTO(0, 5);

        TableReservationCustomerDto response = tableService.findReservationsByTableId(tableId, simplePageRequestDTO);

        assertNotNull(response);
        assertEquals(2L, response.getReservations().size());
        assertEquals(tableId, response.getId());
        assertEquals("Table1", response.getName());
    }

}