package demo.config;

import demo.dao.UserRepository;
import demo.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.TimeZone;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User Klemen = new User("Klemen",
                    TimeZone.getTimeZone("USA"),
                    "USA",
                    5L);

            User Jan = new User("Jan",
                    TimeZone.getTimeZone("SLO"),
                    "SLO",
                    1L);

            userRepository.saveAll(List.of(Klemen, Jan));
        };
    }
}
