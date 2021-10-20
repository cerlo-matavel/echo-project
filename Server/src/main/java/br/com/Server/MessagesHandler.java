package br.com.Server;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MessagesHandler {

    private int vowels,consonants,characters,messages = 0;
    private String content;

    private ArrayList<String> info = new ArrayList<>();

    public ArrayList<String> getInfo() {
        return info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMessagesNumber() {
        return messages;
    }

    public int getMessagesCount(){
        return messages++;
    }

    //Calculates the number os characters, vowels, consonants and stores it in a list
    public void vowelsConsonants(String content){
        consonants = 0;
        vowels = 0;

        for (int i = 0;i < content.length();i++)
            if(Pattern.matches("[a-zA-Z&&[^aeiouAEIOU]]",String.valueOf(content.charAt(i))))
                consonants++;
            else if (Pattern.matches("[aeiouAEIOU&&[^\\d\\s]]",String.valueOf(content.charAt(i))))
                vowels++;

        characters = content.length();

        info.add("Vowels : "+ vowels
                + "; Consonants : "+ consonants
                + "; Characters : "+ characters
                + "; Message : "+ content+"\n");
    }
}
