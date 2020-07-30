package cist4830.unomaha.tempo.model;

public class Goal {

    private Long id;
    private Long parentId;
    private String goal;
    private String description;
    private Long progress;
    private Long target;
    private String due_date;
    private Integer recurrence_num;
    private String recurrence_freq;
    private Long userId;
    private String created_at;
    private String modified_at;

    public Goal(String goal) {
        this.goal = goal;
    }

    /**
     * Goal Constructor.
     *
     * @param id
     * @param parentId
     * @param goal
     * @param description
     * @param progress
     * @param target
     * @param due_date
     * @param recurrence_num
     * @param recurrence_freq
     * @param userId
     * @param created_at
     * @param modified_at
     */
    public Goal(Long id, Long parentId, String goal, String description, Long progress, Long target, String due_date,
                Integer recurrence_num, String recurrence_freq, Long userId, String created_at, String modified_at) {
        this.setId(id);
        this.setParentId(parentId);
        this.setGoal(goal);
        this.setDescription(description);
        this.setProgress(progress);
        this.setTarget(target);
        this.setDueDate(due_date);
        this.setRecurrence_num(recurrence_num);
        this.setRecurrence_freq(recurrence_freq);
        this.setUserId(userId);
        this.setCreatedAt(created_at);
        this.setModifiedAt(modified_at);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getGoal() {
        return this.goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Integer getRecurrence_num() {
        return this.recurrence_num;
    }

    public void setRecurrence_num(int num) {
        this.recurrence_num = num;
    }

    public void setRecurrence_freq(String freq) {
        this.recurrence_freq = freq;
    }

    public String getRecurrence_freq() {
        return this.recurrence_freq;
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

    public String getDueDate() {
        return this.due_date;
    }

    public void setDueDate(String due_date) {
        this.due_date = due_date;
    }

    public String getDueDateFormatted() {
        return this.due_date.split(" ")[0];
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return String.format("{id: %d, goal: %s, description: %s, user: %d",
                this.getId(), this.getGoal(), this.getDescription(), this.getUserId());
    }
}
