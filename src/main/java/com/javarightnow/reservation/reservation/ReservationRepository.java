package com.javarightnow.reservation.reservation;

import com.javarightnow.reservation.repository.IGenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface ReservationRepository extends IGenericRepository<ReservationEntity, Long> {

    /**
     * If it returns 0, it means the table is available and it can be reserved between the given dates.
     * If it returns > 0, it means that table has already reserved by someone else.
     *
     * @param tableId
     * @param inputFrom
     * @param inputTo
     * @return
     */
    @Query("select count(r) from ReservationEntity r where r.table.id = :tableId" +
            " and not (" +
            " not (r.timesLot.from >= :inputFrom and r.timesLot.from < :inputTo)" +
            " and " +
            " not (r.timesLot.to > :inputFrom and r.timesLot.to <= :inputTo)" +
            " )"
    )
    long countOfConflictedRecords(@Param("tableId") Long tableId,
                                  @Param("inputFrom") LocalDateTime inputFrom,
                                  @Param("inputTo") LocalDateTime inputTo);

    List<ReservationEntity> findByTableId(Long tableId);

}