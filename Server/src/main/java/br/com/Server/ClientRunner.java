package br.com.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientRunner {

    //private MessagesHandler messagesHandler = new MessagesHandler();
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Socket socket;
    private ArrayList<ReceivedMessage> info = new ArrayList<>();

    public ClientRunner(BufferedReader bufferedReader, PrintWriter printWriter, Socket socket) {
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
        this.socket = socket;
    }

    public void runner() {

        User user = null;
        ReceivedMessage receivedMessage;


        try {
            user = new User(bufferedReader.readLine(), bufferedReader.readLine());
        } catch (IOException e) {
            LOGGER.fatal("Couldn't create constructor", e);
            //e.printStackTrace();
        }

        LOGGER.info(user.getName() + " has connected to the server.");

        //Handling messages
        while (true) {

            try {
                receivedMessage = new ReceivedMessage(bufferedReader.readLine());

                //Closing socket and printing messages
                if (receivedMessage.getMessage().equalsIgnoreCase("exit")) {
                    socket.close();
                    LOGGER.info(user.getName() + " has disconnected from the server after sending "
                            + info.size() + " messages.\n"
                            + printAllMessages()
                    );
                    return;
                } else {
                    printWriter.println(receivedMessage);
                    info.add(receivedMessage);
                }

            } catch (IOException e) {
                LOGGER.error("Connection lost with user " + user.getName() + ".", e);
                return;
            }
        }
    }
    private String printAllMessages(){
        int i = 0;
        String concatMessages = "";

        while(info.size() > i){
            concatMessages = concatMessages + info.get(i);
            i++;
        }
        return concatMessages;
    }
}