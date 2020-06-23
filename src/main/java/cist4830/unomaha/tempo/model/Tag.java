package cist4830.unomaha.tempo.model;

public class Tag {
	private Long id;
	private String tag;
	private String description;
	private User user;
	private String created_at;
	private String modified_at;

	public Tag(Long id, String tag, String description, User user, String created_at, String modified_at) {
		this.id = id;
		this.tag = tag;
		this.description = description;
		this.user = user;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTag() {
		return this.tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCreatedAt() {
		return this.created_at;
	}
	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}
	public String getModifiedAt() {
		return this.modified_at;
	}
	public void setModifiedAt(String modified_at) {
		this.modified_at = modified_at;
	}
}
