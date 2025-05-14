package com.nexoscript.javaro;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.function.Supplier;

public class JavaroServer {

    private final int port;
    private HttpServer server;
    private Supplier<Object> rootComponentFactory;

    public JavaroServer(int port) {
        this.port = port;
    }

    public void setRootComponent(Supplier<Object> rootComponentFactory) {
        this.rootComponentFactory = rootComponentFactory;
    }

    public void start() {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new RootHandler(this.rootComponentFactory));
            server.createContext("/event", new EventHandler(this.rootComponentFactory));
            server.setExecutor(null);
            server.start();
            System.out.println("Javaro Server run on http://localhost:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
