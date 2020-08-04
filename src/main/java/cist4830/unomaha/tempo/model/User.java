package cist4830.unomaha.tempo.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 998876L;

    private Long id;
    private String name;
    private String username;
    private String password;
    private String created_at;
    private String modified_at;

    public User(Long id, String name, String username, String password, String created_at, String modified_at) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return String.format("{id: %d, name: %s, username: %s", this.getId(), this.getName(), this.getUsername());
    }
}