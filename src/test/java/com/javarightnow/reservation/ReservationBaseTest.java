package com.javarightnow.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

/**
 * Base Test for all the test classes.
 * By default every query will be rolled back because of @Transactional.
 *
 * @author hadi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReservationApplication.class)
@Transactional
@ActiveProfiles("test")
public class ReservationBaseTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void test() {
    }
}
