package com.manuelsrleon.pruebaspring;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) " +
                "VALUES (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR')");
        jdbcTemplate.execute("INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) " +
                "VALUES (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR')");
        jdbcTemplate.execute("INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) " +
                "VALUES (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR')");
        jdbcTemplate.execute("INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) " +
                "VALUES (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR')");
    }
    
    //Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void testGetPriceResponse_1() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T10:00:00Z")
                .param("productId", "35455")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                // Assert response content
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.applicationDate", is("2020-06-14T10:00:00")))
                .andExpect(jsonPath("$.price", is(35.5)));
    }
    //Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void testGetPriceResponse_2() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T16:00:00Z")
                .param("productId", "35455")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                // Assert response content
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.priceList", is(2)))
                .andExpect(jsonPath("$.applicationDate", is("2020-06-14T16:00:00")))
                .andExpect(jsonPath("$.price", is(25.45)));
    }
    //Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void testGetPriceResponse_3() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-14T21:00:00Z")
                .param("productId", "35455")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                // Assert response content
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.applicationDate", is("2020-06-14T21:00:00")))
                .andExpect(jsonPath("$.price", is(35.5)));
    }
    //Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void testGetPriceResponse_4() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-15T10:00:00Z")
                .param("productId", "35455")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                // Assert response content
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.priceList", is(3)))
                .andExpect(jsonPath("$.applicationDate", is("2020-06-15T10:00:00")))
                .andExpect(jsonPath("$.price", is(30.5)));
    }
    //Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void testGetPriceResponse_5() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/prices")
                .param("applicationDate", "2020-06-16T21:00:00Z")
                .param("productId", "35455")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                // Assert response content
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.priceList", is(4)))
                .andExpect(jsonPath("$.applicationDate", is("2020-06-16T21:00:00")))
                .andExpect(jsonPath("$.price", is(38.95)));
    }
    //Test Not Found
    @Test
    public void testGetPriceResponse_NotFound() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/prices")
                .param("applicationDate", "2019-06-14T15:01:00Z")
                .param("productId", "35455")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isNotFound());
    }
    
}
