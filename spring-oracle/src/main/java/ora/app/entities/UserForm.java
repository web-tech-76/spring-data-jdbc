package ora.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserForm implements Serializable, SQLData {

    private Integer id;
    private String email;
    private String role;
    private String password;


    @Override
    public String getSQLTypeName() throws SQLException {
        return "USERTYPES";
    }

    @Override
    public void readSQL(SQLInput sqlInStream, String typeName) throws SQLException {
        setId(sqlInStream.readInt());
        setEmail(sqlInStream.readString());
        setRole(sqlInStream.readString());
        setPassword(sqlInStream.readString());
    }

    @Override
    public void writeSQL(SQLOutput sqlOutStream) throws SQLException {


        if(getId() != null)
            sqlOutStream.writeInt( getId());
        else
            sqlOutStream.writeObject(null);

        sqlOutStream.writeString(getEmail());
        sqlOutStream.writeString(getRole());
        sqlOutStream.writeString(getPassword());


    }
}

