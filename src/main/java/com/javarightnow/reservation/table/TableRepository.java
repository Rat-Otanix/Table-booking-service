package com.javarightnow.reservation.table;

import com.javarightnow.reservation.repository.IGenericRepository;
import com.javarightnow.reservation.table.specificfind.CustomerReservationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface TableRepository extends IGenericRepository<TableEntity, Long> {
    TableEntity findByName(String name);

    @Query("select new com.javarightnow.reservation.table.specificfind.CustomerReservationDto(cu.name, re.timesLot.from, re.timesLot.to) " +
            " from ReservationEntity re join CustomerEntity cu on re.customer.id = cu.id where re.table.id = :tableId")
    List<CustomerReservationDto> findByTableId(final @Param("tableId") Long tableId , Pageable pageable);
}

