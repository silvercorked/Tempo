package cist4830.unomaha.tempo.model;

public class GoalTagAssoc {

    private Long id;
    private Long goalId;
    private Long tagId;
    private String created_at;

    public GoalTagAssoc(Long id, Long goalId, Long tagId, String created_at) {
        this.setId(id);
        this.setGoalId(goalId);
        this.setTagId(tagId);
        this.setCreatedAt(created_at);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoalId() {
        return this.goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public Long getTagId() {
        return this.tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getCreatedAt() {
        return this.created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }
}