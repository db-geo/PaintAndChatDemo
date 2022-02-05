package fr.imt.ales.cepi.demo.chat;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * An encoder for Message objects. Converts Message objects into JSON strings
 */
public class MessageEncoder implements Encoder.Text<Message> {

    /**
     * The helper object for JSON decoding
     */
    private static Gson gson = new Gson();


    /**
     * Convert Message object into a JSON string
     *
     * @param message the message to be encoded
     */
    @Override
    public String encode(Message message) throws EncodeException {
        String json = gson.toJson(message);
        return json;
    }

    /**
     * Initialize the encoder
     *
     * @param endpointConfig configuration of the endPoint
     */
    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    /**
     * Destroy the encoder
     */
    @Override
    public void destroy() {
    }
}