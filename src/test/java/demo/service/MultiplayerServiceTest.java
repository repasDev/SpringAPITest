package demo.service;

import demo.dao.UserRepository;
import demo.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.TimeZone;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class MultiplayerServiceTest {

    @Autowired
    private UserRepository userRepository;
    private MultiplayerService multiplayerService = new MultiplayerService(userRepository);


    @Test
    void testMultiplayerStatusShouldReturnTrue() {
        // given
        String countryCode = "USA";
        long timesPlayed = 10;

        // when
        boolean expected = multiplayerService.canUserPlayMultiplayer(timesPlayed, countryCode);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void testMultiplayerStatusShouldReturnFalseIfCountryCodeFalse() {
        // given
        String countryCode = "SLO";
        long timesPlayed = 10;

        // when
        boolean expected = multiplayerService.canUserPlayMultiplayer(timesPlayed, countryCode);

        // then
        assertThat(expected).isFalse();
    }

    @Test
    void testMultiplayerStatusShouldReturnFalseIfTimesPlayedTooLow() {
        // given
        String countryCode = "SLO";
        long timesPlayed = 4;

        // when
        boolean expected = multiplayerService.canUserPlayMultiplayer(timesPlayed, countryCode);

        // then
        assertThat(expected).isFalse();
    }

    @Test
    void userPlayTimeShouldGetIncrementedBy1() {
        // given
        String userName = "JackOne";
        User user = new User(1L,
                userName,
                TimeZone.getTimeZone("SLO"),
                "SLO",
                5L);

        // when
        multiplayerService.incrementUserPlayTime(user.getId(), user);

        // then
        assertThat(user.getTimesPlayed()).isEqualTo(6);
    }
}