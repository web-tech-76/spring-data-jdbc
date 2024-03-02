package ora.app.repositories;

import ora.app.entities.UserEntity;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {

    @Procedure(name = "fetchAll")
    List<UserEntity> findAllByUserEntity();

    @Procedure(name = "insertUsers", outputParameterName = "out_result")
    Integer insertUsers(@Param("in_email") String email,
                        @Param("in_role") String role,
                        @Param("in_password") String password);




}
