package org.dashboard.common;

public enum Role {
    OWNER("owner"),
    ADMIN("admin"),
    EDITOR("editor"),
    VIEWER("viewer"),
    NONE("none");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
