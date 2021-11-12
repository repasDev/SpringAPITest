package demo.controller;

import demo.model.User;
import demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/admin/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.FOUND)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}", produces = "application/json")
    @ResponseStatus(code = HttpStatus.FOUND)
    public String getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String registerNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteUser(@PathVariable("userId") Long userId) {
        // Check if null
        return userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}", produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public String updateUser(@PathVariable("userId") Long userId,
                           @RequestParam String userName) {
        return userService.updateUser(userId, userName);
    }
}
