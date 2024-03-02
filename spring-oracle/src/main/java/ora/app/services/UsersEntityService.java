package ora.app.services;


import ora.app.entities.UserEntity;
import ora.app.entities.UserShared;
import ora.app.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersEntityService {

    private final UserEntityRepository userEntityRepository;


    public UsersEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    @Transactional
    public List<UserShared> findAll() throws SQLException {
        var userList = new ArrayList<UserShared>();
        var resultList = this.userEntityRepository.findAllByUserEntity();

        for (UserEntity user : resultList) {
            var userShared = new UserShared();
            userShared.setId(user.getId());
            userShared.setEmail(user.getEmail());
            userShared.setRole(user.getRole());
            userShared.setPassword(user.getPassword());
            userList.add(userShared);
        }
        return userList;
    }

    @Transactional
    public Integer insertUsers(UserShared userShared) throws SQLException {
        var result = this.userEntityRepository
                .insertUsers(userShared.getEmail(),
                        userShared.getRole(), userShared.getPassword());
        return result;

    }




}
