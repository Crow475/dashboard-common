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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof UserOfDashboard) {
            UserOfDashboard other = (UserOfDashboard) obj;
            return this.username.equals(other.username) && this.dashboardName.equals(other.dashboardName);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.username.hashCode() + this.dashboardName.hashCode();
    }
}
