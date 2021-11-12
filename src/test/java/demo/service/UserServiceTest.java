package demo.service;

import demo.dao.UserRepository;
import demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        String userName = "JackOne";
        user = new User(1L,
                userName,
                TimeZone.getTimeZone("SLO"),
                "SLO",
                5L);
    }

    @Test
    void canGetAllUsers() {
        // when
        userService.getUsers();

        // then
        verify(userRepository).findAll();
    }

    @Test
    void canDeleteUser() {
        // TODO
    }

    @Test
    @Disabled
    void getUser() {
        // TODO
    }

    @Test
    void canAddNewUser() {
        // given

        // when
        userService.addNewUser(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User captorUserValue = userArgumentCaptor.getValue();

        assertThat(captorUserValue).isEqualTo(user);
    }

    @Test
    void willThrowIfUserByIdDoesNotExist() {
        // given
        given(userRepository.existsById(user.getId())).willReturn(false);

        // then
        assertThatThrownBy(() -> userService.checkIfUserExists(user.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("User with id: " + user.getId() + " does not exist");
    }

    @Test
    void willNotThrowIfUserByIdExists() {
        // given
        given(userRepository.existsById(user.getId())).willReturn(true);

        // then
        assertThatCode(() -> userService.checkIfUserExists(user.getId())).doesNotThrowAnyException();
    }

    @Test
    void willThrowIfUserNameTaken() {
        // given

        given(userRepository.findUserByUserName(user.getUserName())).willReturn(Optional.of(user));

        // then
        assertThatThrownBy(() -> userService.checkIfNameTaken(user))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Username taken");
    }

    @Test
    void willNotThrowIfUserNameNotTaken() {
        // given

        given(userRepository.findUserByUserName(user.getUserName())).willReturn(Optional.empty());

        // then
        assertThatCode(() -> userService.checkIfNameTaken(user)).doesNotThrowAnyException();
    }

    @Test
    void willThrowIfUserNameEmpty() {
        // given
        String userName = "";

        // then
        assertThatThrownBy(() -> userService.checkIfUsernameValid(userName))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Invalid name");
    }

    @Test
    void willThrowIfUserNameNull() {
        // given
        String userName = null;

        // then
        assertThatThrownBy(() -> userService.checkIfUsernameValid(userName))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Invalid name");
    }


    @Test
    void willNotThrowIfUserNameValid() {
        // given
        String userName = "JackOne";

        // then
        assertThatCode(() -> userService.checkIfUsernameValid(userName)).doesNotThrowAnyException();
    }


    @Test
    @Disabled
    void deleteUser() {
    }
}