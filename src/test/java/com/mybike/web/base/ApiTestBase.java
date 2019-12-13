package com.mybike.web.base;

import com.mybike.base.TestBase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTestBase extends TestBase {
    @LocalServerPort
    protected int port;

    protected String getFullUrl(String route) {
        return "http://localhost:" + port + route;
    }

    protected TestRestTemplate getRestTemplate() {
        return new TestRestTemplate();
    }

    protected TestRestTemplate getRestTemplate(String username, String password) {
        if(username != null && password !=null) {
            return new TestRestTemplate(username, password);
        }

        return getRestTemplate();
    }
}
