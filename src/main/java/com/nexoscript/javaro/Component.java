package com.nexoscript.javaro;

import java.util.*;
import java.util.function.Consumer;

public abstract class Component {
    protected String id;
    protected List<Component> children = new ArrayList<>();

    public Component() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void add(Component child) {
        children.add(child);
    }

    public List<Component> getChildren() {
        return children;
    }

    public abstract String getTagName();

    public String render() {
        StringBuilder html = new StringBuilder();
        html.append("<").append(getTagName())
                .append(" id=\"").append(id).append("\">");

        for (Component child : children) {
            html.append(child.render());
        }

        html.append("</").append(getTagName()).append(">");
        return html.toString();
    }

    protected Map<String, Consumer<Event>> eventHandlers = new HashMap<>();

    public void on(String eventType, Consumer<Event> handler) {
        eventHandlers.put(eventType, handler);
    }

    public void trigger(String eventType) {
        Consumer<Event> handler = eventHandlers.get(eventType);
        if (handler != null) {
            handler.accept(new Event(this, eventType));
        }
    }

    public Map<String, Consumer<Event>> getEventHandlers() {
        return eventHandlers;
    }
}

