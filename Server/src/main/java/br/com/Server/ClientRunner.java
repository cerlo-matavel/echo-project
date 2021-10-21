package br.com.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRunner {

    //private MessagesHandler messagesHandler = new MessagesHandler();
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

        MessagesHandler receivedMessage = new MessagesHandler(0,0,0,"");
        MessagesHandler objectInsideList;

        //Handling messages
        while (true) {

            try {
                receivedMessage.setContent(br.readLine());

                //Closing socket and printing messages
                if (receivedMessage.getContent().equalsIgnoreCase("exit")) {
                    socket.close();
                    LOGGER.info(user.getName() + " has disconnected from the server after sending "
                            + receivedMessage.getMessages() + " messages.\n"+
                            receivedMessage.printAllMessages()
                    );
                    return;
                } else {
                    //Sending message to client and analizing it
                    receivedMessage
                            .vowelsConsonants(receivedMessage.getContent());
                    objectInsideList = receivedMessage.getInfo().get(receivedMessage.getMessages());
                    out.println(
                            "Vowels : " + objectInsideList.getVowels()
                            +", consonants : "+ objectInsideList.getConsonants()
                            +", characters : "+ objectInsideList.getCharacters()
                            +", message : "+ objectInsideList.getContent()
                    );
                    receivedMessage.messagesCounter();
                }

            } catch (IOException e) {
                LOGGER.error("Connection lost with user " + user.getName() + ".", e);
                return;
            }
        }
    }
}
