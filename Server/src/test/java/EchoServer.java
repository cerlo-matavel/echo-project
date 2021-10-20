/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author Cerlo Matavel
 */
/*
Criar classe Server com metodos start e stop
no construtor do servidor/cliente deve-se receber o numero da porta que o programa vai escutar as conexoes
#nao criar setter sem necessidade
Criar uma classe ClientHandler(Runnable). Deve receber o socket no construtor
* */

public class EchoServer {

    private static final Logger LOGGER = LogManager.getLogger(EchoServer.class);

    public static void main(String[] args) {

        try {
            LOGGER.info("Waitong for clients...");
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/minhasPropriedades.properties"));

            ServerSocket sose = new ServerSocket(Integer.parseInt(props.getProperty("port")));

            while (true) {
                Socket sock = sose.accept();
                new Thread(new EchoThread(sock)).start();
            }


        } catch (IOException e) {
            //System.out.println("Cant find database information");
            LOGGER.fatal("Cant find server information",e);
            //e.printStackTrace();
        }

    }

    static class EchoThread implements Runnable {
        protected Socket sock;

        public EchoThread(Socket sock) {
            this.sock = sock;
        }

        @Override
        public void run() {

        }
    }
}