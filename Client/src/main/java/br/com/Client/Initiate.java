package br.com.Client;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Properties;

public class Initiate {

    public void initiate(){
        String message = "";

        try {
            System.out.println("Client Started");

            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/minhasPropriedades.properties"));

            Socket sock = new Socket(props.getProperty("url2"), Integer.parseInt(props.getProperty("port")));

            //nome do usuario
            System.out.println("What is the user's name?");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            message = br.readLine();
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            out.println(message);

            //email do usuario
            System.out.println("What is the user's email?");
            message = br.readLine();
            out.println(message);

            while(true) {
                //Recebe a mensagem do cliente
                System.out.println("\nWaiting for messages: ");
                br = new BufferedReader(new InputStreamReader(System.in));
                message = br.readLine();

                //Envia para o servidor/porta
                out.println(message);

                if(!message.equalsIgnoreCase("exit")) {
                    //Recebe a mensagem do servidor
                    br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                    System.out.println(br.readLine());
                }
                else{
                    break;
                }

            }
        } catch (ConnectException e){
            System.out.println("Client couldnt connect to the server");
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Cant find server information");
        }
    }
}
