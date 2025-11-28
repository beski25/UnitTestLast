package com.example.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllProducts_ok() throws Exception {
        mvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    void getById_notFound() throws Exception {
        mvc.perform(get("/products/999")).andExpect(status().isNotFound());
    }

    @Test
    void create_success() throws Exception {
        mvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":1,\"name\":\"Tea\",\"price\":10}"))
            .andExpect(status().isOk());
    }
}
