package demo.controller;

import demo.service.AdsService;
import demo.service.CustomerSupportService;
import demo.service.MultiplayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/services")
public class ServicesController {

    private final MultiplayerService multiplayerService;
    private final CustomerSupportService customerSupportService;
    private final AdsService adsService;


    // Is this ok?
    public ServicesController(MultiplayerService multiplayerService,
                              CustomerSupportService customerSupportService,
                              AdsService adsService) {
        this.multiplayerService = multiplayerService;
        this.customerSupportService = customerSupportService;
        this.adsService = adsService;
    }

    @GetMapping(path = "status")
    public ResponseEntity<Object> checkServicesStatus(@RequestParam String timeZone,
                                                      @RequestParam String userId,
                                                      @RequestParam String countryCode) throws IOException {

        boolean multiplayer = multiplayerService.getStatus(Long.parseLong(userId), countryCode);
        boolean customerSupport = customerSupportService.getStatus();
        boolean ads = adsService.getStatus(countryCode);

        // Construct response
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("multiplayer", multiplayer);
        map.put("customer-support", customerSupport);
        map.put("ads", ads);

        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
}
