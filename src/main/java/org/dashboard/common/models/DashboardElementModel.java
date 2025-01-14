package org.dashboard.common.models;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class DashboardElementModel implements Serializable {
    public enum Type {
        BUTTON("Button"),
        CLOCK("Clock"),
        ANNOUNCEMENT("Announcement"),
        TASKS("Tasks"),
        NOTES("Notes"),
        LINKS("Links"),
        EMBED("Embed");

        public final String name;
        private Type(String name) {
            this.name = name;
        }
    }

    private HashMap<String, String> properties;
    private Type type;

    public DashboardElementModel(Type type, HashMap<String, String> elementProperties) {
        this.properties = elementProperties;
        this.type = type;
    }

    public void updateProperty(String key, String value) {
        this.properties.put(key, value);
    }

    public String getName() {
        return this.type.name + " Element";
    }

    public Type getType() {
        return this.type;
    }

    public HashMap<String, String> getProperties() {
        return this.properties;
    }
}
