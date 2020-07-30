package cist4830.unomaha.tempo.repository;

import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //create
    public User create(User user) {
        String sql = "INSERT INTO user(username, password, name, created_at, modified_at) VALUES (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getCreatedAt());
            statement.setString(5, user.getModifiedAt());
            return statement;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            Long newUserId = keyHolder.getKey().longValue();
            user.setId(newUserId);
        }
        return user;
    }

    //retrieve
    public List<User> findAll() {
        String sql = "SELECT * FROM tag";
        return this.jdbcTemplate.query(sql, new UserMapper());
    }

    //retrieve
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper()));
    }

    public Optional<User> findUserById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return this.jdbcTemplate.query(sql,
                rs -> rs.next() ? Optional.ofNullable(new UserMapper().mapRow(rs, 1)) : Optional.empty()
                , id
        );
    }

    //update
    public boolean update(User user) {
        String sql = "UPDATE user " +
                "SET username = ?, password = ?, name = ?, created_at = ?, modified_at = ? " +
                "WHERE id = ?";
        Object[] params = new Object[]{user.getUsername(), user.getPassword(), user.getName(),
                user.getCreatedAt(), user.getModifiedAt()};
        return this.jdbcTemplate.update(sql, params) == 1;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        Object[] params = new Object[]{id};
        return this.jdbcTemplate.update(sql, params) == 1;
    }
}