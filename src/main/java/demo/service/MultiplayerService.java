package demo.service;

import demo.dao.UserRepository;
import demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MultiplayerService {

    private final UserRepository userRepository;

    @Autowired
    public MultiplayerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean getStatus(Long userId, String countryCode) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new IllegalStateException("User with id: " + userId + " does not exist"));

        boolean multiplayerAvailable = canUserPlayMultiplayer(user.getTimesPlayed(), countryCode);
        incrementUserPlayTime(userId, user);
        userRepository.save(user);
        return multiplayerAvailable;
    }

    public void incrementUserPlayTime(Long userId, User user) {
        user.setTimesPlayed(user.getTimesPlayed() + 1);
    }

    public boolean canUserPlayMultiplayer(Long timesPlayed, String countryCode) {
        Integer multiplayerAvailableThreshold = 5;

        System.out.println(timesPlayed);
        System.out.println(countryCode);
        return timesPlayed >= multiplayerAvailableThreshold && Objects.equals(countryCode, "USA");
    }
}
