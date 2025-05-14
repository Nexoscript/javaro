package com.nexoscript.test;

import com.nexoscript.javaro.JavaroServer;
import com.nexoscript.test.views.MainView;

public class JavaroTest {

    public static void main(String[] args) {
        JavaroServer server = new JavaroServer(8080);
        server.setRootComponent(MainView::new);
        server.start();
    }
}
