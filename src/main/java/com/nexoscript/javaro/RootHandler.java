package com.nexoscript.javaro;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Supplier;

public class RootHandler implements HttpHandler {
    private Supplier<Object> rootComponentFactory;

    public RootHandler(Supplier<Object> rootComponentFactory) {
        this.rootComponentFactory = rootComponentFactory;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Object view = this.rootComponentFactory.get();
        String response = "<html><head><title>Javaro</title></head><body><div id='app'>";

        if (view instanceof Component) {
            ComponentRegistry.register((Component) view);
            response += ((Component) view).render();
        } else {
            response += "Failed Root-Component!";
        }
        response += "</div><script>" +
                "document.addEventListener(\"click\", e => {" +
                "  const t = e.target.closest('[data-component-id]');" +
                "  if (!t) return;" +
                "  fetch('/event', {" +
                "    method: 'POST'," +
                "    headers: {'Content-Type': 'application/json'}," +
                "    body: JSON.stringify({ id: t.dataset.componentId, type: 'click' })" +
                "  }).then(r => r.text()).then(html => {" +
                "    document.getElementById('app').innerHTML = html;" +
                "  });" +
                "});</script></body></html>";

        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
