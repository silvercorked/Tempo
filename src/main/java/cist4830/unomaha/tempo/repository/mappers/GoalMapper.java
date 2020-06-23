package cist4830.unomaha.tempo.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.UserRepository;

public class GoalMapper implements RowMapper<Goal> {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GoalRepository goalRepository;

	@Override
	public Goal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Optional<User> possibleUser = userRepository.findUserById(rs.getLong("user_id"));
		Optional<Goal> possibleGoal = goalRepository.findGoalById(rs.getLong("parent_id"));
		User user = null;
		Goal parent = null;
		if (possibleUser.isPresent())
			user = possibleUser.get();
		if (possibleGoal.isPresent())
			parent = possibleGoal.get();
		Goal tag = new Goal(rs.getLong("id"), parent, rs.getString("goal")
			, rs.getString("description"), rs.getLong("progress"), rs.getLong("target")
			, user , rs.getString("created_at"), rs.getString("modified_at"));	
		return tag;
	}
}