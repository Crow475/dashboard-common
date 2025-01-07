package org.dashboard.common.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.dashboard.common.Pair;

public class DashboardModel implements Serializable {
    public static class pairKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(final String key, final DeserializationContext ctxt) throws java.io.IOException, JsonProcessingException {
        String[] parts = key.split("=");
        return new Pair<Integer, Integer>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
    }
    
    private byte[] id = new byte[16];
    private byte[] ownerId = new byte[16];
    private Date createdAt;
    private Date updatedAt;
    private String name;

    private Properties properties;
    private String JSONProperties;

    public static class Properties implements Serializable {
        private int sizeX;
        private int sizeY;
        private Map<Pair<Integer, Integer>, DashboardElementModel> elements;

        public Properties() {
            this.sizeX = 4;
            this.sizeY = 4;
            this.elements = new HashMap<Pair<Integer, Integer>, DashboardElementModel>();
        }

        public Properties(int sizeX, int sizeY, Map<Pair<Integer, Integer>, DashboardElementModel> elements) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.elements = elements;
        }

        public int getSizeX() {
            return this.sizeX;
        }

        public int getSizeY() {
            return this.sizeY;
        }

        public void setSizeX(int sizeX) {
            this.sizeX = sizeX;
        }

        public void setSizeY(int sizeY) {
            this.sizeY = sizeY;
        }

        public void setSize(int sizeX, int sizeY) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }

        public void setElement(int x, int y, DashboardElementModel element) {
            this.elements.put(new Pair<>(x, y), element);
        }

        public DashboardElementModel getElement(int x, int y) {
            return this.elements.get(new Pair<>(x, y));
        }

        public void removeElement(int x, int y) {
            this.elements.remove(new Pair<>(x, y));
        }

        public void removeElement(DashboardElementModel element) {
            this.elements.values().remove(element);
        }

        public Map<Pair<Integer, Integer>, DashboardElementModel> getElements() {
            return this.elements;
        }

        public void setElements(Map<Pair<Integer, Integer>, DashboardElementModel> elements) {
            this.elements = elements;
        }

        public String toJSONString () {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(this);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public DashboardModel(String name) {
        this.name = name;
        this.properties = new Properties();
    }

    public DashboardModel(byte[] id, byte[] ownerId, Date createdAt, Date updatedAt, String name, Properties properties) {
        this.id = id;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.properties = properties;
        this.JSONProperties = properties.toJSONString();
    }

    public DashboardModel(byte[] id, byte[] ownerId, Date createdAt, Date updatedAt, String name, String JSONProperties) {
        this.id = id;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.JSONProperties = JSONProperties;
    }

    public byte[] getId() {
        return this.id;
    }

    public byte[] getOwnerId() {
        return this.ownerId;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public String getName() {
        return this.name;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(byte[] ownerId) {
        this.ownerId = ownerId;
    }

    public void updateJSONProperties() {
        this.JSONProperties = this.properties.toJSONString();
    }

    public void updatePropertiesFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addKeyDeserializer(Pair.class, new pairKeyDeserializer());
        objectMapper.registerModule(module);
        
        try {
            this.properties = objectMapper.readValue(this.JSONProperties, Properties.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJSONProperties() {
        return this.JSONProperties;
    }

    public void setJSONProperties(String JSONProperties) {
        this.JSONProperties = JSONProperties;
    }
}
