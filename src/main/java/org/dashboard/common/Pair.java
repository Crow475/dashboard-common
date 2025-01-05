package org.dashboard.common;

import java.io.Serializable;
import java.util.Objects;

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
        if (obj instanceof Pair) {
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return this.key.equals(pair.getKey()) && this.value.equals(pair.getValue());
        }
        return false;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
