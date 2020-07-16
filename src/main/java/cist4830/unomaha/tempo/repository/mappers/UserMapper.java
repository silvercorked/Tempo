package cist4830.unomaha.tempo.repository.mappers;

import cist4830.unomaha.tempo.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("username")
                , rs.getString("name"), rs.getString("password")
                , rs.getString("created_at"), rs.getString("modified_at"));
    }
}