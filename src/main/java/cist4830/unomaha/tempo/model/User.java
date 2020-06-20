package cist4830.unomaha.tempo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class User {
	private final @Id Long id;
	private final String name;
	private final String username;
	private final String password;
	private final LocalDate created_at;
	private final LocalDate modified_at;

	static User of(String name, String username, LocalDate created_at) {
		return new User((Long) null, name, username, (String) null, created_at, (LocalDate) null);
	}

	static User of(Long id, String name, String username, LocalDate created_at) {
		return new User(id, name, username, (String) null, created_at, (LocalDate) null);
	}

	public User(Long id, String name, String username, String password, LocalDate created_at, LocalDate modified_at) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.username = username;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	User withId(Long id) {
		return new User(id, this.name, this.username, this.password, this.created_at, this.modified_at);
	}
	public Long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public LocalDate getCreatedAt() {
		return this.created_at;
	}
	public LocalDate getModifiedAt() {
		return this.modified_at;
	}
}