package org.dashboard.common;

import java.util.HashMap;
import java.io.Serializable;

public class Request implements Serializable {
    private String type;
    private HashMap<String, String> message;
    private Object object = null;
    private String token = null;

    public Request(String type, HashMap<String, String> message) {
        this.type = type;
        this.message = message;
    }

    public Request(String type, HashMap<String, String> message, String token) {
        this.type = type;
        this.message = message;
        this.token = token;
    }

    public Request(String type, HashMap<String, String> message, Object object) {
        this.type = type;
        this.message = message;
        this.object = object;
    }

    public Request(String type, HashMap<String, String> message, Object object, String token) {
        this.type = type;
        this.message = message;
        this.object = object;
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public HashMap<String, String> getMessage() {
        return message;
    }

    public Object getObject() {
        return object;
    }

    public String getToken() {
        return token;
    }
}
