package br.com.Server;

public class MainServer {

    public static void main(String[] args) {

        Server server = new Server(1766);
        server.start();

        //server.stop();

    }
}
