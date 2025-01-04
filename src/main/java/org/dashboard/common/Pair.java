package org.dashboard.common;

import java.io.Serializable;

public class Pair<K, V> implements Serializable {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) obj;

        if (key != null ? !key.equals(pair.key) : pair.key != null) {
            return false;
        }

        return value != null ? value.equals(pair.value) : pair.value == null;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
