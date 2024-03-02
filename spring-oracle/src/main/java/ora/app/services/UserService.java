package ora.app.services;

import ora.app.entities.UserForm;
import ora.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserForm> findAll() {
        var usersList = this.userRepository.fetchAll();
        return usersList;
    }

    public Integer addNew(UserForm user) throws SQLException {
        return this.userRepository.insertUser(user);

    }

    public Integer addNewUser(UserForm user) throws SQLException {
        return this.userRepository.insertUserObject(user);
    }

    public Integer addUsers(List<UserForm> users) throws SQLException {

        UserForm []  userForms= new UserForm[users.size()];

        for(int i=0; i< users.size(); i++){
            userForms[i] = users.get(i);
        }

        return this.userRepository.insertUserObjects(userForms);
    }



}
