package com.mybike.web.controllers;

import com.mybike.web.api.models.EnduroResponseModel;
import com.mybike.web.base.ApiTestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnduroApiControllerTest extends ApiTestBase {

    @Test
    void getEnduroBikes_shouldReturnEnduroBikes() {
        EnduroResponseModel[] result = getRestTemplate().getForObject(
                getFullUrl("/api/enduro"), EnduroResponseModel[].class);
        assertEquals(5, result.length);
    }
}
