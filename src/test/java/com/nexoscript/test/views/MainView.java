package com.nexoscript.test.views;

import com.nexoscript.javaro.components.Button;
import com.nexoscript.javaro.components.Div;
import com.nexoscript.javaro.components.Label;

public class MainView extends Div {
    public MainView() {
        Label label = new Label("Welcome to Javaro!");
        Button button = new Button("Click me");
        button.onClick(event -> label.setText("Clicked!"));

        this.add(label);
        this.add(button);
    }
}
