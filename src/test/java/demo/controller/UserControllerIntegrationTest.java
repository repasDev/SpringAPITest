package demo.controller;

import demo.model.User;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {


    @Value("${local.server.port}") int port;

    @Test
    @Order(1)
    void canReturnUsers() throws Exception {
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"userName\": \"Klemen\",\n" +
                "        \"timeZone\": \"GMT\",\n" +
                "        \"countryCode\": \"USA\",\n" +
                "        \"timesPlayed\": 5,\n" +
                "        \"json\": {\n" +
                "            \"empty\": false\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"userName\": \"Jan\",\n" +
                "        \"timeZone\": \"GMT\",\n" +
                "        \"countryCode\": \"SLO\",\n" +
                "        \"timesPlayed\": 1,\n" +
                "        \"json\": {\n" +
                "            \"empty\": false\n" +
                "        }\n" +
                "    }\n" +
                "]";

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/admin/user", String.class);

        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected, response.getBody(), true);

    }

    @Test
    @Order(2)
    void canReturnUser() throws JSONException {
        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"userName\": \"Klemen\",\n" +
                "    \"timeZone\": \"GMT\",\n" +
                "    \"countryCode\": \"USA\",\n" +
                "    \"timesPlayed\": 5\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/admin/user/1", String.class);

        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    @Order(3)
    void canRegisterNewUser() throws JSONException {
        User user = new User("Matt", TimeZone.getTimeZone("USA"), "USA", 3L);
        String expected = "{\n" +
                "    \"id\": 3,\n" +
                "    \"userName\": \"Matt\",\n" +
                "    \"timeZone\": \"GMT\",\n" +
                "    \"countryCode\": \"USA\",\n" +
                "    \"timesPlayed\": 3\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<User>(user, header);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/api/v1/admin/user", request, String.class);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    @Order(4)
    void canUpdateUser() throws JSONException {
        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"userName\": \"Alex\",\n" +
                "    \"timeZone\": \"GMT\",\n" +
                "    \"countryCode\": \"USA\",\n" +
                "    \"timesPlayed\": 5\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> query = new LinkedMultiValueMap<>();
        query.add("userName", "Alex");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(query, headers);

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/admin/user/1", HttpMethod.PUT, request, String.class);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    @Order(5)
    void canDeleteUser() throws JSONException {
        String expected = "Deleted user with id:1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> query = new LinkedMultiValueMap<>();
        query.add("id", "1");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(query, headers);

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/v1/admin/user/1", HttpMethod.DELETE, request, String.class);
        System.out.println(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

}