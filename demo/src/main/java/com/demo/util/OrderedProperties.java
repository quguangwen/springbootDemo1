package com.demo.util;

import java.util.*;

public class OrderedProperties extends Properties {
    private final LinkedHashSet<Object> linkedHashSet = new LinkedHashSet<>();

    public Enumeration<Object> keys() {
        return Collections.<Object> enumeration(linkedHashSet);
    }

    public Object put(Object key, Object value) {
        linkedHashSet.add(key);
        return super.put(key, value);
    }

    @Override
    public Set<Object> keySet() {
        return linkedHashSet;
    }

    @Override
    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<>();
        for(Object key: this.linkedHashSet){
            set.add((String) key);
        }
        return set;
    }



}
