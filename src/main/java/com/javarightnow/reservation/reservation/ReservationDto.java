package com.javarightnow.reservation.reservation;

import com.javarightnow.reservation.dataobject.BaseDto;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto extends BaseDto<Long> {

    private TimesLot timesLot;

    private Long customer;
    private Long table;
}