package cist4830.unomaha.tempo.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import cist4830.unomaha.tempo.model.Tag;
import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.UserRepository;

public class TagMapper implements RowMapper<Tag> {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
		Optional<User> possibleUser = userRepository.findById(rs.getLong("user_id"));
		User user = null;
		if (possibleUser.isPresent())
			user = possibleUser.get();
		Tag tag = new Tag(rs.getLong("id"), rs.getString("tag")
			, rs.getString("description"), user
			, rs.getString("created_at"), rs.getString("modified_at"));	
		return tag;
	}
}