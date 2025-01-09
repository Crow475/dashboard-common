package org.dashboard.common.models;

import java.io.Serializable;

import org.dashboard.common.Role;

public class UserOfDashboard implements Serializable {
    private String username;
    private String dashboardName;
    private Role role;

    public UserOfDashboard(String username, String dashboardName, Role role) {
        this.username = username;
        this.dashboardName = dashboardName;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getDashboardName() {
        return dashboardName;
    }

    public Role getRole() {
        return role;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
