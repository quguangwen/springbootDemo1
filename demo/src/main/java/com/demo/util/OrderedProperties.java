package com.demo.util;

import java.util.*;

public class OrderedProperties extends Properties {
    private final LinkedHashSet<Object> keys = new LinkedHashSet<>();

    public Enumeration<Object> keys() {
        return Collections.<Object> enumeration(keys);
    }

    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    public Set<Object> keySet() {
        return keys;
    }

    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<>();
        for(Object key: this.keys){
            set.add((String) key);
        }
        return set;
    }



}
