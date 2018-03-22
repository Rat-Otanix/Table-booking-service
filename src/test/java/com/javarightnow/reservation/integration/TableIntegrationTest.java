package com.javarightnow.reservation.integration;

import com.javarightnow.reservation.ReservationApplication;
import com.javarightnow.reservation.reservation.TimesLot;
import com.javarightnow.reservation.reservation.ReservationDto;
import com.javarightnow.reservation.table.TableDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Integration Test for Reservation. It does following steps:
 * <ol>
 * <li>create a table</li>
 * <li>make a reservation</li>
 * <li>find all reservation for the created table</li>
 * </ol>
 *
 * @author hadi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ReservationApplication.class, TableIntegrationTest.class}
)
@ActiveProfiles("test")
public class TableIntegrationTest {

    private static final String TABLE_RESOURCE_URL = "/api/v1/table";

    @Autowired
    private TestRestTemplate restTemplate;

    private Long tableId;

    @Test
    public void createTableMakeReservationReadReservations_newValidData_succeed() throws Exception {
        TableDto tableDto = TableDto.builder()
                .name("Table10")
                .build();

        createTable(tableDto);
        makeReservation();
        findReservationsByTableId(tableDto.getName());
    }

    private void createTable(TableDto tableDto) {

        final HttpEntity<TableDto> request = new HttpEntity<>(tableDto);
        final ResponseEntity<Long> response =
                restTemplate.postForEntity(TABLE_RESOURCE_URL, request, Long.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        tableId = response.getBody();
        assertThat(CREATED, notNullValue());
    }

    private void makeReservation() {
        LocalDateTime from = LocalDateTime.of(2050, Month.MARCH, 16, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2050, Month.MARCH, 16, 12, 0, 0);
        ReservationDto reservationDto = ReservationDto.builder()
                .customer(2L)
                .table(1L) // tableId
                .timesLot(new TimesLot(from, to))
                .build();

        final HttpEntity<ReservationDto> request = new HttpEntity<>(reservationDto);
        final ResponseEntity<Void> response =
                restTemplate.postForEntity(TABLE_RESOURCE_URL + "/" + tableId + "/reservation", request, Void.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

    }

    private void findReservationsByTableId(String expectedName) {

        ResponseEntity<TableDto> response =
                restTemplate.getForEntity(TABLE_RESOURCE_URL + "/" + tableId, TableDto.class);
        TableDto tableDto = response.getBody();
        final HttpHeaders httpHeaders = response.getHeaders();

        assertEquals(expectedName, tableDto.getName());
        assertThat(tableDto.getName(), notNullValue());
        assertThat(tableDto.getId(), is(tableId));
        assertThat(tableDto.getReservations().size(), is(1));

        assertThat(response.getStatusCode(), equalTo(HttpStatus.FOUND));
        assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
    }

}