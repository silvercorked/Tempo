package cist4830.unomaha.tempo.database;

import java.util.Arrays;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import cist4830.unomaha.tempo.model.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;


public class TestUser {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	PasswordEncoder passwordEncoder;
	/**
	 * id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  password varchar(255) NOT NULL,
  name varchar(100) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_at DATETIME NOT NULL,
	 */
    @Test
    public void testInsertUsers() {

		List<String> names = Arrays.asList("Bill Billington", "Kathy Geraud", "Opel Rocker");
		List<String> usernames = Arrays.asList("bigBill", "KathG", "Opal");
		List<String> passwords = Arrays.asList("billy123", "drowssap", "rOpeL").stream()
			.map(pass -> passwordEncoder.encode(pass)).collect(Collectors.toList());
		List<String> dates = Arrays.asList("1909-02-13 06:15:24", "2019-10-20 15:59:20", "2012-06-12 19:12:58");
		this.createTable();
		jdbcTemplate.update("INSERT INTO user(username, password, name, created_at, modified_at) VALUES (?,?,?,?,?)",
			usernames, passwords, names, dates, dates);
		List<User> users = (List<User>) jdbcTemplate.query("SELECT id, name, username, created_at FROM user WHERE id = ?", new Object[] {"1"},
			(rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name")
				, rs.getString("username"), rs.getString("password"), LocalDate.parse(rs.getString("created_at"))
				, LocalDate.parse(rs.getString("modified_at"))
			)
		);
        Assertions.assertEquals(users.get(0).getName(), "Kathy Geraud");
    }
	
	public void createTable() {
		jdbcTemplate.execute("CREATE TABLE user (id bigint(20) NOT NULL AUTO_INCREMENT, username varchar(100) NOT NULL, password varchar(255) NOT NULL, name varchar(100) NOT NULL, created_at DATE NOT NULL, modified_at DATE NOT NULL, PRIMARY KEY (id), UNIQUE KEY UK_username (username));");
	}
}
