package com.javarightnow.reservation.table.specificfind;

import com.javarightnow.reservation.dataobject.Dto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TableReservationCustomerDto extends Dto {

    /**
     * table Id
     */
    private Long id;

    /**
     * table Name
     */
    private String name;

    List<CustomerReservationDto> reservations;
}