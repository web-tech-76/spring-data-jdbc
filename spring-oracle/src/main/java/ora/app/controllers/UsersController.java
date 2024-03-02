package ora.app.controllers;


import ora.app.entities.UserForm;
import ora.app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserForm> findAll() {
        var usersList = this.userService.findAll();
        System.out.println("usersList.toString() = " + usersList.toString());
        return usersList;
    }

    @PostMapping("/add")
    public Integer insertNew(@RequestBody UserForm users) throws SQLException {
        return this.userService.addNew(users);
    }

    @PostMapping("/add/user")
    public Integer insertNewUser(@RequestBody UserForm users) throws SQLException {
        return this.userService.addNewUser(users);
    }

    @PostMapping("/add/users")
    public Integer insertUsers(@RequestBody UserForm[] userArray) throws SQLException {
        var users= List.of(userArray);
        return this.userService.addUsers(users);
    }
}
