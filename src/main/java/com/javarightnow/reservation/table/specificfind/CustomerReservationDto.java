package com.javarightnow.reservation.table.specificfind;

import com.javarightnow.reservation.dataobject.Dto;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerReservationDto extends Dto {

    private String customer_name;

    private LocalDateTime from;

    private LocalDateTime to;

    public CustomerReservationDto(String customer_name, LocalDateTime from, LocalDateTime to) {
        this.customer_name = customer_name;
        this.from = from;
        this.to = to;
    }
}