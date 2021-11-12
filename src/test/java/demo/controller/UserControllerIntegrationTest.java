package demo.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class UserControllerIntegrationTest {


    @Test
    @Order(1)
    void getUsers() throws Exception {
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://localhost:8080/api/v1/admin/user", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

    }

    @Test
    void getUser() {
    }

    @Test
    void registerNewUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}