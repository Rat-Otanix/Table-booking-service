package com.javarightnow.reservation.controller;

import com.javarightnow.reservation.table.specificfind.TableReservationCustomerDto;
import com.javarightnow.reservation.aspect.CheckBindingResult;
import com.javarightnow.reservation.dataobject.dto.SimplePageRequestDTO;
import com.javarightnow.reservation.reservation.ReservationDto;
import com.javarightnow.reservation.exception.GeneralException;
import com.javarightnow.reservation.table.TableDto;
import com.javarightnow.reservation.table.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Table Controller
 */
@RestController
@RequestMapping("/api/v1/table")
@Slf4j
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @CheckBindingResult
    @PostMapping
    public ResponseEntity<Long> createTable(@RequestBody @Valid TableDto tableDto,
                                            BindingResult bindingResult) throws GeneralException {
        Long tableId = tableService.save(tableDto).getId();
        return new ResponseEntity<Long>(tableId, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/reservation")
    public ResponseEntity<Void> makeReservation(@PathVariable("id") Long tableId,
                                                @RequestBody @Valid ReservationDto reservationDto,
                                                BindingResult bindingResult) throws GeneralException {
        reservationDto.setTable(tableId);
        tableService.makeReservation(reservationDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TableReservationCustomerDto>
    findReservationsByTableId(@PathVariable("id") Long tableId,
                              @Valid SimplePageRequestDTO simplePageRequestDTO,
                              BindingResult bindingResult) throws GeneralException {
        TableReservationCustomerDto dto = tableService.findReservationsByTableId(tableId, simplePageRequestDTO);
        return new ResponseEntity<TableReservationCustomerDto>(dto, HttpStatus.FOUND);
    }

}