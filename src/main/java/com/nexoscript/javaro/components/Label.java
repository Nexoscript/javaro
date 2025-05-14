package com.nexoscript.javaro.components;

import com.nexoscript.javaro.Component;

public class Label extends Component {
    private String text = "";

    public Label(String text) {
        this.text = text;
    }

    @Override
    public String getTagName() {
        return "span";
    }

    @Override
    public String render() {
        return "<span id=\"" + getId() + "\">" + text + "</span>";
    }

    public void setText(String text) {
        this.text = text;
    }
}

