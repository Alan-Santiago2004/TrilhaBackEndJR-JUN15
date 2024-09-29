package com.codigoCerto.tarefas.models.enums;

public enum RoleUser {
    ADMIN("admin"),
    USER("user");

    private String role;

    RoleUser(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
