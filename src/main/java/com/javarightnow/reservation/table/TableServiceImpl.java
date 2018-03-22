package com.javarightnow.reservation.table;

import com.javarightnow.reservation.customer.CustomerService;
import com.javarightnow.reservation.exception.*;
import com.javarightnow.reservation.exception.enums.Model;
import com.javarightnow.reservation.reservation.ReservationService;
import com.javarightnow.reservation.service.GenericService;
import com.javarightnow.reservation.table.specificfind.CustomerReservationDto;
import com.javarightnow.reservation.table.specificfind.TableReservationCustomerDto;
import com.javarightnow.reservation.dataobject.dto.SimplePageRequestDTO;
import com.javarightnow.reservation.table.converter.TableDtoToEntityConverter;
import com.javarightnow.reservation.table.converter.TableEntityToDtoConverter;
import com.javarightnow.reservation.reservation.ReservationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional
class TableServiceImpl extends GenericService<TableEntity, TableDto, Long> implements TableService {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CustomerService customerService;

    private final TableRepository tableRepository;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository,
                            TableEntityToDtoConverter tableEntityToDtoConverter,
                            TableDtoToEntityConverter tableDtoToEntityConverter) {
        super(tableRepository, tableEntityToDtoConverter, tableDtoToEntityConverter, Model.TABLE);
        this.tableRepository = tableRepository;
    }

    @Override
    public TableDto save(TableDto dto) throws GeneralException {
        checkExists(dto.getName());

        TableEntity ent = dtoToEntityConverter.convert(dto);
        TableEntity save = tableRepository.save(ent);
        return entityToDtoConverter.convert(save);
    }


    @Override
    public void makeReservation(ReservationDto reservationDto) throws GeneralException {
        validate(reservationDto.getTable());
        customerService.validate(reservationDto.getCustomer());
        reservationService.fromLessThanToDates(
                reservationDto.getTimesLot(),
                LocalDateTime::isBefore
        );
        Boolean isAvailable = reservationService.isTableAvailable(reservationDto.getTable(), reservationDto.getTimesLot());
        log.info("Table with id {} is {}. " + reservationDto.getTable(), isAvailable ? "available" : "not available");
        if (isAvailable) {
            reservationService.save(reservationDto);
        } else {
            log.error("Table with id {} is not available." + reservationDto.getTable());
            throw ReservationConflictException.builder()
                    .code(10010L)
                    .message("Table has already reserved. ")
                    .build();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public TableReservationCustomerDto findReservationsByTableId(Long tableId, SimplePageRequestDTO simplePageRequestDTO)
            throws NoSuchResourceException, EmptyInputException {
        validate(tableId);
        TableEntity tableEntity = tableRepository.getOne(tableId);
        PageRequest pageRequest = preparePageRequest(simplePageRequestDTO);

        List<CustomerReservationDto> reservationDtoList = tableRepository.findByTableId(tableId, pageRequest);

        return TableReservationCustomerDto.builder()
                .id(tableEntity.getId())
                .name(tableEntity.getName())
                .reservations(reservationDtoList)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public void checkExists(String name) throws GeneralException {
        if (name == null) {
            throw EmptyInputException.builder()
                    .code(10008L)
                    .message("table name is null.")
                    .build();
        }
        boolean exist = tableRepository.findByName(name) != null;
        if (exist) {
            throw DuplicateResourceException.builder()
                    .code(10009L)
                    .message("The table with the given name already exists!")
                    .build();
        }
    }

}