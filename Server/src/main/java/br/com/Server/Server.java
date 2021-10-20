package br.com.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private int portNumber;
    private ServerSocket serverSocket;
    private Socket socket;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public void start(){
        try {
            LOGGER.info("Server initiated.");

            serverSocket = new ServerSocket(portNumber);
            LOGGER.info("Listening to port "+ portNumber);

            LOGGER.info("Waiting for clients...");
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }

        } catch (IOException e) {
            LOGGER.fatal("Couldnt initiate server",e);
            //e.printStackTrace();
        }
    }

    public void stop(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            LOGGER.fatal("Was not able to close server.");
        }
    }
}
