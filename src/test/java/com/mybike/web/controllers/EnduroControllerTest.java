package com.mybike.web.controllers;

import com.mybike.data.repositories.EnduroRepository;
import com.mybike.web.base.ViewTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class EnduroControllerTest extends ViewTestBase {
    @MockBean
    EnduroRepository enduroRepository;

    @Test
    void getEnduro_whenEnduroDoesNotHaveName_shouldReturnError() {
        String enduroName = "";
        

    }
}
