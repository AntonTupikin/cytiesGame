package org.example;

public class Game {

    private String word;

    public Game(String word){
        this.word=word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public String check(String userWord){
        char c = word.charAt(word.length()-1);
        char b = userWord.charAt(0);
        if(c==b){
            return "Yes";
        } else {
            return "No";
        }
    }
}
