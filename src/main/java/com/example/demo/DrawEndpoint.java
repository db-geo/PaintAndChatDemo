package com.example.demo;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/draw/{username}")
public class DrawEndpoint {

    private static Set<DrawEndpoint> endpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private Session session;

    private static void broadcast(String message) {

        endpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException {

        this.session = session;
        endpoints.add(this);
        users.put(session.getId(), username);

        broadcast(username + "connecté");
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        endpoints.remove(this);
        broadcast(users.get(session.getId()) + " déconnecté");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        //...
    }
}
