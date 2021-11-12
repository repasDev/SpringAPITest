package demo.controller;

import demo.service.AdsService;
import demo.service.CustomerSupportService;
import demo.service.MultiplayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ServicesController.class)
class ServicesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private MultiplayerService multiplayerService;
    private CustomerSupportService customerSupportService;
    private AdsService adsService;

    public ServicesControllerIntegrationTest(MultiplayerService multiplayerService,
                                             CustomerSupportService customerSupportService,
                                             AdsService adsService) {
        this.multiplayerService = multiplayerService;
        this.customerSupportService = customerSupportService;
        this.adsService = adsService;
    }


    String path = "api/v1/services";

    @Test
    void test(@RequestParam String timeZone,
              @RequestParam String userId,
              @RequestParam String countryCode) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get(path + "/status")
                .param("timeZone", "GMT").param("userId", "2").param("countryCode", "USA");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("Yo", result.getResponse().getContentAsString());

    }

}