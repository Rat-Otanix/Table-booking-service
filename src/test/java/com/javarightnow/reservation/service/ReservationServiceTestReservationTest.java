package com.javarightnow.reservation.service;

import com.javarightnow.reservation.ReservationBaseTest;
import com.javarightnow.reservation.reservation.ReservationService;
import com.javarightnow.reservation.reservation.TimesLot;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;

public class ReservationServiceTestReservationTest extends ReservationBaseTest {

    @Autowired
    private ReservationService reservationService;

    private Long tableId = 1L;

    @Test
    public void checkTableAvailability_validDateTime_availableTest() {

        TimesLot[] timesLots = new TimesLot[3];
        timesLots[0] = new TimesLot(
                LocalDateTime.of(2050, Month.MARCH, 16, 11, 0, 0),
                LocalDateTime.of(2050, Month.MARCH, 16, 12, 0, 0)
        );
        timesLots[1] = new TimesLot(
                LocalDateTime.of(2050, Month.MARCH, 16, 8, 0, 0),
                LocalDateTime.of(2050, Month.MARCH, 16, 9, 0, 0)
        );
        timesLots[2] = new TimesLot(
                LocalDateTime.of(2050, Month.MARCH, 16, 10, 0, 0),
                LocalDateTime.of(2050, Month.MARCH, 16, 11, 0, 0)
        );

        Boolean available = reservationService.isTableAvailable(tableId, timesLots[new Random().nextInt(timesLots.length)]);
        Assert.assertTrue(available);
    }

    @Test
    public void checkTableAvailability_invalidDateTime_notAvailableTest() {

        TimesLot[] timesLots = new TimesLot[3];
        timesLots[0] = new TimesLot(
                LocalDateTime.of(2050, Month.MARCH, 16, 9, 30, 0),
                LocalDateTime.of(2050, Month.MARCH, 16, 11, 0, 0)
        );
        timesLots[1] = new TimesLot(
                LocalDateTime.of(2050, Month.MARCH, 16, 8, 30, 0),
                LocalDateTime.of(2050, Month.MARCH, 16, 9, 30, 0)
        );
        timesLots[2] = new TimesLot(
                LocalDateTime.of(2050, Month.MARCH, 16, 9, 0, 0),
                LocalDateTime.of(2050, Month.MARCH, 16, 10, 0, 0)
        );

        Boolean available = reservationService.isTableAvailable(tableId, timesLots[new Random().nextInt(timesLots.length)]);
        Assert.assertFalse(available);
    }

}