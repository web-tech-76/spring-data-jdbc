package ora.app.controllers;


import ora.app.entities.UserShared;
import ora.app.services.UsersEntityService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users/entity")
public class UsersEntityController {

    private final UsersEntityService usersEntityService;


    public UsersEntityController(UsersEntityService usersEntityService) {
        this.usersEntityService = usersEntityService;
    }

    @GetMapping("/")
    public List<UserShared> findAll() throws SQLException {
        return this.usersEntityService.findAll();
    }

    @PostMapping("/add")
    public Integer addNew(@RequestBody UserShared userShared) throws SQLException {
        return this.usersEntityService.insertUsers(userShared);
    }

}
