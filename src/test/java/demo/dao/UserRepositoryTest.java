package demo.dao;

import demo.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.TimeZone;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void itShouldFindUserByUsername() {
        // given
        String userName = "JackOne";
        User user = new User(userName,
                TimeZone.getTimeZone("SLO"),
                "SLO",
                5L);

        userRepository.save(user);

        // when
        boolean exists = userRepository.findUserByUserName(userName).isPresent();

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldNotFindNonExistingUserByUsername() {
        // given
        String userName = "JackOne";

        // when
        boolean exists = userRepository.findUserByUserName(userName).isPresent();

        // then
        assertThat(exists).isFalse();
    }
}