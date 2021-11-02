package br.com.Server;

import java.util.regex.Pattern;

public class ReceivedMessage {

    private int vowels,consonants,characters;
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ReceivedMessage = " +
                "vowels: " + vowels +
                ", consonants: " + consonants +
                ", characters: " + characters +
                ", content: " + message;
    }

    //Constructor
    public ReceivedMessage(String message) {
        this.message = message;
        messageComposition();
    }

    //Calculates the number os characters, vowels, consonants and stores it in a list
    private void messageComposition(){

        consonants = 0;
        vowels = 0;

        for (int i = 0; i < message.length(); i++)
            if(Pattern.matches("[a-zA-Z&&[^aeiouAEIOU]]",String.valueOf(message.charAt(i))))
                consonants++;
            else if (Pattern.matches("[aeiouAEIOU&&[^\\d\\s]]",String.valueOf(message.charAt(i))))
                vowels++;

        characters = message.length();
    }
}
