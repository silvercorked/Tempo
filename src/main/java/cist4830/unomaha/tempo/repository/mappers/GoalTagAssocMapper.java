package cist4830.unomaha.tempo.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cist4830.unomaha.tempo.model.GoalTagAssoc;

@Component
public class GoalTagAssocMapper implements RowMapper<GoalTagAssoc> {

	@Override
	public GoalTagAssoc mapRow(ResultSet rs, int rowNum) throws SQLException {
		GoalTagAssoc goalTagAssoc = new GoalTagAssoc(rs.getLong("id"), rs.getLong("goal_id")
			, rs.getLong("tag_id"), rs.getString("created_at"));	
		return goalTagAssoc;
	}
}