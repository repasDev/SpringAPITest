package demo.service;

import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class CustomerSupportService {
    public boolean getStatus() {
        int hour = ZonedDateTime.now(ZoneId.of("Europe/Ljubljana")).getHour();
        return checkIfAvailable(hour);
    }

    public boolean checkIfAvailable(int hour) {
        return 9 <= hour && hour < 15;
    }
}
