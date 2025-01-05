package org.dashboard.common.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.dashboard.common.Pair;

public class DashboardModel implements Serializable {
    private byte[] id = new byte[16];
    private byte[] ownerId = new byte[16];
    private Date createdAt;
    private Date updatedAt;
    private String name;

    private Properties properties;

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
}