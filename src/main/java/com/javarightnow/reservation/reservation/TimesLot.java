package com.javarightnow.reservation.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * it shows the duration of a reservation.
 */
@Embeddable
@Access(AccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimesLot {

    @Column(name = "RES_RSRV_FROM")
    private LocalDateTime from;

    @Column(name = "RES_RSRV_TO")
    private LocalDateTime to;
}