package com.nexoscript.javaro.components;

import com.nexoscript.javaro.Component;
import com.nexoscript.javaro.Event;

import java.util.function.Consumer;

public class Button extends Component {
    private String label;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public String getTagName() {
        return "button";
    }

    @Override
    public String render() {
        return "<button id=\"" + getId() + "\" data-component-id=\"" + getId() + "\">" + label + "</button>";
    }

    public void onClick(Consumer<Event> handler) {
        this.on("click", handler);
    }
}

