package ora.app.repositories;


import ora.app.entities.UserForm;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserForm> fetchAll() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkg_users")
                .withProcedureName("fetchAll")
                .declareParameters(new SqlParameter[]{
                        new SqlOutParameter("resultSet", Types.REF_CURSOR)
                })
                .returningResultSet("resultSet", new RowMapper<UserForm>() {
                    @Override
                    public UserForm mapRow(ResultSet rs, int rowNum) throws SQLException {
                        var users = new UserForm();
                        users.setId(rs.getInt("ID"));
                        users.setEmail(rs.getString("EMAIL"));
                        users.setRole(rs.getString("ROLE"));
                        users.setPassword(rs.getString("PASSWORD"));
                        return users;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> result = simpleJdbcCall.execute(mapSqlParameterSource);
        var usersList = (List<UserForm>) result.get("resultSet");
        return usersList;
    }

    public Integer insertUser(UserForm user) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkg_users")
                .withProcedureName("insertUsers")
                .declareParameters(new SqlParameter[]{
                        //new SqlParameter("in_id", OracleTypes.INTEGER),
                        new SqlParameter("in_email", OracleTypes.VARCHAR),
                        new SqlParameter("in_role", OracleTypes.VARCHAR),
                        new SqlParameter("in_password", OracleTypes.VARCHAR),
                        new SqlOutParameter("out_result", OracleTypes.INTEGER)
                });

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        //mapSqlParameterSource.addValue("in_id", user.getId());
        mapSqlParameterSource.addValue("in_email", user.getEmail());
        mapSqlParameterSource.addValue("in_role", user.getRole());
        mapSqlParameterSource.addValue("in_password", user.getPassword());

        Map<String, Object> mappedResult = simpleJdbcCall.execute(mapSqlParameterSource);
        var result = (Integer) mappedResult.get("out_result");
        return result;
    }


    public Integer insertUserObject(UserForm user) throws SQLException {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkg_users")
                .withProcedureName("insertUsersObject")
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("user", OracleTypes.STRUCT, user.getSQLTypeName()),
                        new SqlOutParameter("out_result", OracleTypes.INTEGER)
                });

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("user", user);
        Map<String, Object> mappedResult = simpleJdbcCall.execute(mapSqlParameterSource);
        var result = (Integer) mappedResult.get("out_result");
        return result;
    }



    public Integer insertUserObjects(UserForm... users) throws SQLException {


        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pkg_users")
                .withProcedureName("insertUsersObjects")
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("user_array", OracleTypes.ARRAY, "userTypes"),
                        new SqlOutParameter("out_result", OracleTypes.INTEGER)
                });

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("user_array", users);
        Map<String, Object> mappedResult = simpleJdbcCall.execute(mapSqlParameterSource);
        var result = (Integer) mappedResult.get("out_result");
        return result;
    }


}
