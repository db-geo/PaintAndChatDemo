package fr.imt.ales.cepi.demo.chat;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket server endpoint for the chat. The connexion must provide a username in the URL. Messages are
 * encoded/decoded as JSON by MessageEncoder/MessageDecoder
 */
@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {
    /**
     * Set of connected endPoints
     */
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    /**
     * Map of connected usernames by session IDs
     */
    private static HashMap<String, String> users = new HashMap<>();
    /**
     * Current session
     */
    private Session session;

    /**
     * Broadcasts a message to all connected endpoints
     */
    private static void broadcast(Message message) {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Handle an open message (connection) : as defined in the value attribute of the ServerEnpoint annotation, a username
     * parameter is expected
     *
     * @param session  the session of the connexion
     * @param username the username parameter in the URL
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);
        broadcast(new Message(username, "Connected!"));
    }

    /**
     * Receive a message from a client
     *
     * @param session session of the connexion
     * @param message the message (previously converted)
     * @throws IOException
     * @throws EncodeException
     */
    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    /**
     * Handle a close event : remote the current endpoint from the enpoints set and broadcast a disconnected message to
     * others.
     *
     * @param session session of the connexion
     */
    @OnClose
    public void onClose(Session session) {
        chatEndpoints.remove(this);
        broadcast(new Message(users.get(session.getId()), "Disconnected!"));
    }

    /**
     * Handle an error event
     * @param session session of the connexion
     * @param throwable the error
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        // TODO : error handling
    }
}