package cist4830.unomaha.tempo.repository;

import cist4830.unomaha.tempo.controllers.errors.ResourceNotFoundException;
import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.model.Tag;
import cist4830.unomaha.tempo.repository.mappers.GoalMapper;
import cist4830.unomaha.tempo.repository.mappers.GoalTagAssocMapper;
import cist4830.unomaha.tempo.repository.mappers.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public class GoalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //create
    public Goal create(Goal goal) {
        String sql = "INSERT INTO goal(goal, description, progress, target, due_date, user_id, recurrence_num, recurrence_freq, created_at, modified_at) VALUES (?,?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, goal.getGoal());
            statement.setString(2, goal.getDescription());
            statement.setLong(3, goal.getProgress());
            statement.setLong(4, goal.getTarget());
            statement.setString(5, goal.getDueDate());
            statement.setLong(6, goal.getUserId());
            statement.setInt(7, goal.getRecurrence_num());
            statement.setString(8, goal.getRecurrence_freq());
            statement.setString(9, goal.getCreatedAt());
            statement.setString(10, goal.getModifiedAt());
            return statement;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            Long newGoalId = keyHolder.getKey().longValue();
            goal.setId(newGoalId);
        }
        return goal;
    }

    //retrieve
    public List<Goal> findAll() {
        String sql = "SELECT * FROM goal";
        return this.jdbcTemplate.query(sql, new GoalMapper());
    }

    public List<Goal> findAllByUserId(Long userId) {
        String sql = "SELECT * FROM goal WHERE user_id = ?";
        return this.jdbcTemplate.query(sql, new Object[]{userId}, new GoalMapper());
    }

    public Optional<Goal> findGoalByIdAndUserId(Long id, Long userId) {
        String sql = "SELECT * FROM goal WHERE id = ? AND user_id = ?";
        return this.jdbcTemplate.query(sql,
                rs -> rs.next() ? Optional.ofNullable(new GoalMapper().mapRow(rs, 1)) : Optional.empty()
                , id, userId
        );
    }

    //retrieve
    public Optional<Goal> findById(Long id) {
        String sql = "SELECT * FROM goal WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new GoalMapper()));
    }

    public Optional<Goal> findGoalById(Long id) {
        String sql = "SELECT * FROM goal WHERE id = ?";
        return this.jdbcTemplate.query(sql,
                rs -> rs.next() ? Optional.ofNullable(new GoalMapper().mapRow(rs, 1)) : Optional.empty()
                , id
        );
    }

    //update
    public boolean update(Goal goal) {
        String sql = "UPDATE goal " +
                "SET goal = ?, description = ?, progress = ?, target = ?, due_date = ?, user_id = ?, created_at = ?, modified_at = ? " +
                "WHERE id = ?";
        // TODO this needs the recurrence information
        Object[] params = new Object[]{goal.getGoal(), goal.getDescription(), goal.getProgress(), goal.getTarget(),
                goal.getDueDate(), goal.getUserId(), goal.getCreatedAt(), goal.getModifiedAt(), goal.getId()
        };
        return this.jdbcTemplate.update(sql, params) == 1;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM goal WHERE id = ?";
        Object[] params = new Object[]{id};
        return this.jdbcTemplate.update(sql, params) == 1;
    }

    //associate a tag
    public Long associateTag(Goal goal, Tag tag) {
        List<Tag> tags = getTags(goal);
        String sql;
        if (tags.stream().anyMatch((Tag t) -> tag.getId() == t.getId())) {
            sql = "SELECT * FROM goal_tag_assoc WHERE goal_id = ? AND tag_id = ?";
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql
                    , new Object[]{goal.getId(), tag.getId()}, new GoalTagAssocMapper()))
                    .orElseThrow(() -> {
                        throw new ResourceNotFoundException();
                    })
                    .getId();
        } else {
            sql = "INSERT INTO goal_tag_assoc(goal_id, tag_id, created_at) VALUES (?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, goal.getId());
                statement.setLong(2, tag.getId());
                statement.setString(3, new Date(new java.util.Date().getTime()).toString());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                return keyHolder.getKey().longValue();
            }
            return null;
        }
    }

    //dissociate a tag
    public boolean disassociateTag(Goal goal, Tag tag) {
        String sql = "DELETE FROM goal_tag_assoc WHERE goal_id = ? AND tag_id = ?";
        return this.jdbcTemplate.update(sql, new Object[]{goal.getId(), tag.getId()}) == 1;
    }

    //gather tags
    public List<Tag> getTags(Goal goal) {
        String sql = "SELECT t.*, gta.tag_id, gta.goal_id FROM goal_tag_assoc AS gta LEFT JOIN tag AS t ON t.id = gta.tag_id WHERE gta.goal_id = ?";
        return this.jdbcTemplate.query(sql,
                new TagMapper(),
                goal.getId()
        );
    }
}
