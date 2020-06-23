package cist4830.unomaha.tempo.model;

public class Goal {

    private Long id;
    private Goal parent;
    private String goal;
    private String description;
    private Long progress;
    private Long target;
    private User user;
    private String created_at;
    private String modified_at;
    
    public Goal(String goal) {
        this.goal = goal;
    }
    public Goal(Long id, Goal parent, String goal, String description, Long progress, Long target, User user, String created_at, String modified_at) {
        this.setId(id);
        this.setParent(parent);
        this.setGoal(goal);
        this.setDescription(description);
        this.setProgress(progress);
        this.setTarget(target);
        this.setUser(user);
        this.setCreatedAt(created_at);
        this.setModifiedAt(modified_at);
    }
    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Goal getParent() {
        return this.parent;
    }
    public void setParent(Goal parent) {
        this.parent = parent;
    }
    public String getGoal() {
        return this.goal;
    }
    public void setGoal(String goal) {
        this.goal = goal;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getProgress() {
        return this.progress;
    }
    public void setProgress(Long progress) {
        this.progress = progress;
    }
    public Long getTarget() {
        return this.target;
    }
    public void setTarget(Long target) {
        this.target = target;
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
    
    @Override
	public String toString() {
		return String.format("{id: %d, goal: %s, description: %s, user: %s",
			this.getId(), this.getGoal(), this.getDescription(), this.getUser().toString());
	}
}
