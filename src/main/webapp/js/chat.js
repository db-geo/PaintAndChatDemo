/** A class that represents a websocket client. It connects to the server with a username, prints all received
 * messages in the log field and can send the content of the message field to the server */
class ChatClient {
    /** connection */
    webSocket;

    /** Connects to the server, save the connexion and listen for the messagessend by the server in JSON, in
     * order to print them in the log field
     * */
    connect() {
        let username = document.getElementById("username").value;
        let host = document.location.host;
        let pathname = document.location.pathname;
        // connect using the same base URL as the current document location
        this.webSocket = new WebSocket("ws://" + host + pathname + "/" + username);
        // Listen to the messages from the server
        this.webSocket.onmessage = (event) => {
            // Print the message on the browser console
            console.log(event.data);
            // Append the message to the log field
            let log = document.getElementById("log");
            let message = JSON.parse(event.data);
            log.innerHTML += message.from + " : " + message.content + "\n";
        };
    }

    /** Send the content of the message field to the server, in JSON */
    send() {
        let json = JSON.stringify({
            "content": document.getElementById("msg").value
        });
        this.webSocket.send(json);
    }
}