package cist4830.unomaha.tempo.repository;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.model.Tag;
import cist4830.unomaha.tempo.repository.mappers.GoalMapper;
import cist4830.unomaha.tempo.repository.mappers.TagMapper;
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
public class TagRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //create
    public Tag create(Tag tag) {
        String sql = "INSERT INTO tag(tag, description, user_id, created_at, modified_at) VALUES (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tag.getTag());
            statement.setString(2, tag.getDescription());
            statement.setLong(3, tag.getUserId());
            statement.setString(4, tag.getCreatedAt());
            statement.setString(5, tag.getModifiedAt());
            return statement;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            Long newTagId = keyHolder.getKey().longValue();
            tag.setId(newTagId);
        }
        return tag;
    }

    //retrieve
    public List<Tag> findAll() {
        String sql = "SELECT * FROM tag";
        return this.jdbcTemplate.query(sql, new TagMapper());
    }

    //retrieve
    public Optional<Tag> findById(Long id) {
        String sql = "SELECT * FROM tag WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new TagMapper()));
    }

    public Optional<Tag> findTagById(Long id) {
        String sql = "SELECT * FROM tag WHERE id = ?";
        return this.jdbcTemplate.query(sql,
                rs -> rs.next() ? Optional.ofNullable(new TagMapper().mapRow(rs, 1)) : Optional.empty()
                , id
        );
    }

    //update
    public boolean update(Tag tag) {
        String sql = "UPDATE tag " +
                "SET tag = ?, description = ?, user_id = ?, created_at = ?, modified_at = ? " +
                "WHERE id = ?";
        Object[] params = new Object[]{tag.getTag(), tag.getDescription(), tag.getUserId(),
                tag.getCreatedAt(), tag.getModifiedAt()
        };
        return this.jdbcTemplate.update(sql, params) == 1;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM tag WHERE id = ?";
        Object[] params = new Object[]{id};
        return this.jdbcTemplate.update(sql, params) == 1;
    }

    //gather goals
    public List<Goal> getGoals(Tag tag) {
        String sql = "SELECT g.*, gta.tag_id, gta.goal_id FROM goal_tag_assoc AS gta LEFT JOIN goal AS g ON g.id = gta.goal_id WHERE gta.tag_id = ?";
        return this.jdbcTemplate.query(sql,
                new GoalMapper(),
                tag.getId()
        );
    }
}