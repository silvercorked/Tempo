package cist4830.unomaha.tempo.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cist4830.unomaha.tempo.model.Tag;

@Component
public class TagMapper implements RowMapper<Tag> {

	@Override
	public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tag tag = new Tag(rs.getLong("id"), rs.getString("tag")
			, rs.getString("description"), rs.getLong("user_id")
			, rs.getString("created_at"), rs.getString("modified_at"));	
		return tag;
	}
}