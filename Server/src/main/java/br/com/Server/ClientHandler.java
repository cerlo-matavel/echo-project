package br.com.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/*
* Server tem de responder com o total de vogais, consoantes e caracteres e manter o registro de cada mensagem
e tem de estar em uma lista separada para os usuarios
* */
public class ClientHandler implements Runnable{

    protected Socket socket;
    private ReceivedMessage receivedMessage;
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    private BufferedReader br = null;
    private PrintWriter out = null;

    @Override
    public void run() {

        LOGGER.info("Connection established");
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            LOGGER.fatal("",e);
        }


        new ClientRunner(br,out,socket).runner();
    }
}
