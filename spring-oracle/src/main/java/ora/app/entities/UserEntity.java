package ora.app.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")

@NamedStoredProcedureQuery(
        name = "fetchAll",
        resultClasses = UserEntity.class,
        procedureName = "pkg_users.fetchAll", parameters = {
        @StoredProcedureParameter(name = "out_result", mode = ParameterMode.REF_CURSOR, type = void.class)
})
@NamedStoredProcedureQuery(
        name = "insertUsers",
        procedureName = "pkg_users.insertUsers",
        parameters = {
                @StoredProcedureParameter(name = "in_email", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "in_role", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "in_password", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "out_result", mode = ParameterMode.OUT, type = Integer.class)
        })


public class UserEntity  {

    @Id
    private Integer id;

    private String email;
    private String role;
    private String password;

}
