package ru.generic;

public class Role extends Base {

    private final String userRole;

    public Role(String id, String role) {
        super(id);
        this.userRole = role;
    }

    public String getRole() {
        return userRole;
    }
}