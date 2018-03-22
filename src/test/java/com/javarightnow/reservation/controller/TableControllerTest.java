package com.javarightnow.reservation.controller;

import com.javarightnow.reservation.ReservationBaseTest;
import com.javarightnow.reservation.table.TableDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author h.mohammadi
 */
public class TableControllerTest extends ReservationBaseTest {

    private static final String TABLE_RESOURCE_URL = "/api/v1/table";

    @Before
    public void init() {
    }

    @Test
    public void createTable_duplicateName_conflict() throws Exception {
        TableDto tableDto = TableDto.builder()
                .name("Table1")
                .build();
        mockMvc.perform(post(TABLE_RESOURCE_URL)
                .content(objectMapper.writeValueAsString(tableDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("The table with the given name already exists!")))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void post_emptyTable_badRequest() throws Exception {
        TableDto tableDto = TableDto.builder()
                .build();
        mockMvc.perform(post(TABLE_RESOURCE_URL)
                .content(objectMapper.writeValueAsString(tableDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findReservationsByTableId_validTableId_succeed() throws Exception {
        Long tableId = 3L;

        MvcResult mvcResult = mockMvc.perform(get(TABLE_RESOURCE_URL + "/" + tableId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(content().string(containsString("Table3")))
                .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();

        TableDto tableDto = objectMapper.readValue(responseJson, TableDto.class);
        assertEquals("The count of reservations should be 1", 1, tableDto.getReservations().size());
    }

}
