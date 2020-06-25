package cist4830.unomaha.tempo.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.UserRepository;

@Component
public class GoalMapper implements RowMapper<Goal> {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GoalRepository goalRepository;

	@Override
	public Goal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Goal tag = new Goal(rs.getLong("id"), rs.getLong("parent_id"), rs.getString("goal")
			, rs.getString("description"), rs.getLong("progress"), rs.getLong("target")
			, rs.getLong("user_id"), rs.getString("created_at"), rs.getString("modified_at"));	
		return tag;
	}
}