package br.com.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunner {

    private MessagesHandler messagesHandler = new MessagesHandler();
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);

    BufferedReader br;
    PrintWriter out;
    Socket socket;

    public ClientRunner(BufferedReader br, PrintWriter out, Socket socket) {
        this.br = br;
        this.out = out;
        this.socket = socket;
    }

    public void runner() {


        User user = null;
        //Criacao do construtor
        try {
            user = new User(br.readLine(), br.readLine());
        } catch (IOException e) {
            LOGGER.fatal("Couldn't create constructor", e);
            //e.printStackTrace();
        }

        LOGGER.info(user.getName() + " has connected to the server.");

        //Handling messages
        while (true) {
            try {
                messagesHandler.setContent(br.readLine());

                if (messagesHandler.getContent().equalsIgnoreCase("exit")) {
                    socket.close();
                    LOGGER.info(user.getName() + " has disconnected from the server after sending "
                            + messagesHandler.getMessagesNumber() + " messages.\n"
                            + messagesHandler.getInfo()
                    );
                    return;
                } else {
                    messagesHandler
                            .vowelsConsonants(messagesHandler.getContent());
                    out.println("Server: " + messagesHandler
                            .getInfo()
                            .get(messagesHandler.getMessagesNumber()));
                    messagesHandler.getMessagesCount();
                }

            } catch (IOException e) {
                LOGGER.error("Connection lost with user " + user.getName() + ".", e);
                return;
            }
        }
    }
}
