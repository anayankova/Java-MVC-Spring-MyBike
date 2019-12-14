package com.mybike.web.controllers;

import com.mybike.web.base.ViewTestBase;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class EnduroControllerTest extends ViewTestBase {

    @Test
    void getEnduro() throws Exception {
        mockMvc.perform(get("/bikes/create/enduro"))
                .andExpect(status().isOk())
                .andExpect(view().name("bikes/create-enduro"));
    }

    @Test
    void postEnduro() throws Exception {
        mockMvc.perform(post("/bikes/create/enduro"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

}
