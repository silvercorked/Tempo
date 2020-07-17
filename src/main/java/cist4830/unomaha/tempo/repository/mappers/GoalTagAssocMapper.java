package cist4830.unomaha.tempo.repository.mappers;

import cist4830.unomaha.tempo.model.GoalTagAssoc;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GoalTagAssocMapper implements RowMapper<GoalTagAssoc> {

    @Override
    public GoalTagAssoc mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GoalTagAssoc(rs.getLong("id"), rs.getLong("goal_id")
                , rs.getLong("tag_id"), rs.getString("created_at"));
    }
}