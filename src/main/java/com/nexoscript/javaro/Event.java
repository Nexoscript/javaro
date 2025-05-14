package com.nexoscript.javaro;

public class Event {
    private final Component source;
    private final String type;

    public Event(Component source, String type) {
        this.source = source;
        this.type = type;
    }

    public Component getSource() {
        return source;
    }

    public String getType() {
        return type;
    }
}
