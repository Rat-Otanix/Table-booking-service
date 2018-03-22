package com.javarightnow.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarightnow.reservation.exception.GeneralException;
import com.javarightnow.reservation.table.TableService;
import com.javarightnow.reservation.table.TableDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author hadi
 */
@RunWith(SpringRunner.class)
@WebMvcTest({TableService.class, TableController.class})
@AutoConfigureMockMvc
public class TableMockControllerTest {

    private static final String TABLE_RESOURCE_URL = "/api/v1/table";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TableService tableService;

    @Before
    public void init() throws GeneralException {

        TableDto tableDto = TableDto.builder()
                .name("Table1")
                .build();
        tableDto.setId(1L);
        tableDto.setVersion(1L);
        tableDto.setCreator("User1");

        when(tableService.save(any(TableDto.class)))
                .thenReturn(tableDto);
    }

    @Test
    public void createTable_validData_created() throws Exception {
        Long id = 1L;

        TableDto tableDto = TableDto.builder()
                .name("Table1")
                .build();

        String result = mockMvc.perform(post(TABLE_RESOURCE_URL)
                .content(objectMapper.writeValueAsString(tableDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long response = objectMapper.readValue(result, Long.class);
        Assert.assertEquals(id, response);
    }

}