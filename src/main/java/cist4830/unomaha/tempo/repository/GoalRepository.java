package cist4830.unomaha.tempo.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.repository.mappers.GoalMapper;

@Component
@Repository
public class GoalRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//create
	public Goal create(Goal goal) {
		String sql = "INSERT INTO goal(goal, description, progress, target, due_date, user_id, created_at, modified_at) VALUES (?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, goal.getGoal());
			statement.setString(2, goal.getDescription());
			statement.setLong(3, goal.getProgress());
			statement.setLong(4, goal.getTarget());
			statement.setString(5, goal.getDueDate());
			statement.setLong(6, goal.getUserId());
			statement.setString(7, goal.getCreatedAt());
			statement.setString(8, goal.getModifiedAt());
			return statement;
		}, keyHolder);

		Long newGoalId = keyHolder.getKey().longValue();
		goal.setId(newGoalId);
		return goal;
	}
	//retrieve
	public List<Goal> findAll() {
		String sql = "SELECT * FROM goal";
		return this.jdbcTemplate.query(sql, new GoalMapper());
	}
	//retrieve
	public Optional<Goal> findById(Long id) {
		String sql = "SELECT * FROM goal WHERE id = ?";
		return Optional.of(jdbcTemplate.queryForObject(sql, new Object[] { id }, new GoalMapper()));
	}
	public Optional<Goal> findGoalById(Long id) {
		String sql = "SELECT * FROM goal WHERE id = ?";
		return this.jdbcTemplate.query(sql,
			rs -> rs.next() ? Optional.of(new GoalMapper().mapRow(rs, 1)) : Optional.empty()
			, id
		);
	}
	//update
	public boolean update(Goal goal) {
		String sql = "UPDATE goal " +
		"SET goal = ?, description = ?, progress = ?, target = ?, due_date = ?, user_id = ?, created_at = ?, modified_at = ? " +
		"WHERE id = ?";
		Object[] params = new Object[]{goal.getGoal(), goal.getDescription(), goal.getProgress(),
			goal.getTarget(), goal.getDueDate(), goal.getUserId(), goal.getCreatedAt(), goal.getModifiedAt()
		};
		return this.jdbcTemplate.update(sql, params) == 1;
	}
	public boolean delete(Long id) {
		String sql = "DELETE FROM goal WHERE id = ?";
		Object[] params = new Object[]{ id };
		return this.jdbcTemplate.update(sql, params) == 1;
	}
}