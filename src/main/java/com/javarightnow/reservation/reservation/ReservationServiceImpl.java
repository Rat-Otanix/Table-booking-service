package com.javarightnow.reservation.reservation;

import com.javarightnow.reservation.exception.InvalidInputException;
import com.javarightnow.reservation.exception.enums.Model;
import com.javarightnow.reservation.reservation.converter.ReservationDtoToEntityConverter;
import com.javarightnow.reservation.reservation.converter.ReservationEntityToDtoConverter;
import com.javarightnow.reservation.service.GenericService;
import com.javarightnow.reservation.table.TableEntity;
import com.javarightnow.reservation.customer.CustomerEntity;
import com.javarightnow.reservation.util.IDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
class ReservationServiceImpl extends GenericService<ReservationEntity, ReservationDto, Long> implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationEntityToDtoConverter reservationEntityToDtoConverter,
                                  ReservationDtoToEntityConverter reservationDtoToEntityConverter) {
        super(reservationRepository, reservationEntityToDtoConverter, reservationDtoToEntityConverter, Model.RESERVATION);
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationDto save(ReservationDto reservationDto) {
        ReservationEntity reservationEntity = dtoToEntityConverter.convert(reservationDto);
        setTransientFields(reservationEntity);
        ReservationEntity save = reservationRepository.save(reservationEntity);
        return entityToDtoConverter.convert(save);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean isTableAvailable(Long tableId, TimesLot timesLot) {
        long countOfConflictedRecords = reservationRepository
                .countOfConflictedRecords(tableId, timesLot.getFrom(), timesLot.getTo());
        return countOfConflictedRecords <= 0;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservationDto> findByTableId(Long tableId) {
        List<ReservationEntity> entList = reservationRepository.findByTableId(tableId);
        return entityToDtoConverter.convert(entList);
    }

    @Transactional(readOnly = true)
    @Override
    public void fromLessThanToDates(TimesLot timesLot, IDateTime dateTime) throws InvalidInputException {
        Boolean compare = dateTime.compare(timesLot.getFrom(), timesLot.getTo());
        if (compare) {
            return;
        }
        throw new InvalidInputException("The incoming dates are not correct. from date (" + timesLot.getFrom() +
                ") must less than to date (" + timesLot.getTo() + ")", 111L);
    }

    private void setTransientFields(ReservationEntity reservationEntity) {
        reservationEntity.setCustomer(entityManager.getReference(CustomerEntity.class, reservationEntity.getCustomer().getId()));
        reservationEntity.setTable(entityManager.getReference(TableEntity.class, reservationEntity.getTable().getId()));
    }
}