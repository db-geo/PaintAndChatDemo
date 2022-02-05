package fr.imt.ales.cepi.demo.chat;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * A decoder for JSON messages. Converts JSON Strings into Message objects
 */
public class MessageDecoder implements Decoder.Text<Message> {

    /**
     * The helper object for JSON decoding
     */
    private static Gson gson = new Gson();

    /**
     * Convert a JSON string in a Message object
     *
     * @param s the JSON string to be decoded
     */
    @Override
    public Message decode(String s) throws DecodeException {
        return gson.fromJson(s, Message.class);
    }

    /**
     * Indicates if a message has to be decodes
     *
     * @param s the message
     * @return
     */
    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    /**
     * Initialize the decoder
     *
     * @param endpointConfig configuration of the endPoint
     */
    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    /**
     * Destroy the decoder
     */
    @Override
    public void destroy() {
    }
}