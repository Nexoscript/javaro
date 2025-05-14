package com.nexoscript.javaro;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.eztxm.ezlib.config.object.JsonObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

public class EventHandler implements HttpHandler {
    private Supplier<Object> rootComponentFactory;

    public EventHandler(Supplier<Object> rootComponentFactory) {
        this.rootComponentFactory = rootComponentFactory;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Handle Event");
        String body = new String(exchange.getRequestBody().readAllBytes());
        System.out.println(body);
        JsonObject json = JsonObject.parse(body);
        String id = json.getConverted("id").asString();
        String type = json.getConverted("type").asString();

        Component component = ComponentRegistry.get(id);
        if (component != null) {
            component.trigger(type);
        }

        Object view = rootComponentFactory.get();
        if (view instanceof Component) {
            ComponentRegistry.clear();
            ComponentRegistry.register((Component) view);

            String response = ((Component) view).render();
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
