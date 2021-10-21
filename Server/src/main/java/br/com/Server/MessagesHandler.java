package br.com.Server;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MessagesHandler {

    private int vowels,consonants,characters,messages;
    private String content;

    private ArrayList<MessagesHandler> info = new ArrayList<>();

    public ArrayList<MessagesHandler> getInfo() {
        return info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int messagesCounter(){
        return messages++;
    }


    public int getVowels() {
        return vowels;
    }

    public int getConsonants() {
        return consonants;
    }

    public int getCharacters() {
        return characters;
    }

    public int getMessages() {
        return messages;
    }

    //Constructor
    public MessagesHandler(int vowels, int consonants, int characters, String content) {
        this.vowels = vowels;
        this.consonants = consonants;
        this.characters = characters;
        this.content = content;
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
        info.add(new MessagesHandler(vowels,consonants,characters,this.content));
    }

    public String printAllMessages(){
        int i = 0;
        String mess = "";
        MessagesHandler object;

        while(info.size() > i){
            object = info.get(i);
            mess = mess + "Vowels : " + object.getVowels()
                    +", consonants : "+ object.getConsonants()
                    +", characters : "+ object.getCharacters()
                    +", message : "+ object.getContent() + "\n";
            i++;
        }
        return mess;
    }
}
