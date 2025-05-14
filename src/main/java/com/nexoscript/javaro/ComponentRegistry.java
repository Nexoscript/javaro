package com.nexoscript.javaro;

import java.util.HashMap;
import java.util.Map;

public class ComponentRegistry {
    private static final Map<String, Component> components = new HashMap<>();

    public static void register(Component component) {
        components.put(component.getId(), component);
        for (Component child : component.getChildren()) {
            register(child);
        }
    }

    public static Component get(String id) {
        return components.get(id);
    }

    public static void clear() {
        components.clear();
    }
}
