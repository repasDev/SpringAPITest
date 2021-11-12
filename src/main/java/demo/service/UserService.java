package demo.service;

import demo.dao.UserRepository;
import demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String getUser(Long userId) {
        checkIfUserExists(userId);
        User user = userRepository.getById(userId);
        return user.getJson().toString();
    }

    public void addNewUser(User user) {
        checkIfNameTaken(user);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        checkIfUserExists(userId);
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String userName) {
        checkIfUserExists(userId);
        User user = userRepository.getById(userId);
        checkIfUsernameValid(userName, user);
        checkIfNameTaken(user);
        user.setUserName(userName);
    }

    public void checkIfNameTaken(User user) {
        Optional<User> userOptional = userRepository.findUserByUserName(user.getUserName());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("Username taken");
        }
    }

    public void checkIfUserExists(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "User with id: " + userId + " does not exist"
            );
        }
    }

    public void checkIfUsernameValid(String userName, User user) {
        if (userName == null || userName.length() < 1 || !Objects.equals(user.getUserName(), userName)) {
            throw new IllegalStateException("Invalid name");
        }
    }
}

