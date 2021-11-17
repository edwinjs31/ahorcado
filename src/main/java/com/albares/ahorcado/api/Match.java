package com.albares.ahorcado.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Match {

    private final String[] WORDS = {"coche", "lapiz", "mesa", "españa", "europa", "instituto", "sol", "clase", "java", "lenguaje"};
    private char[] underscoreFromWord;
    private final AtomicInteger idGamers = new AtomicInteger(0);
    private final TreeMap<Integer, Gamer> gamers = new TreeMap();

    private Integer turn = 1;
    private String secretWord;
    private boolean complete;

    public Match() {
    }

    public int addGamer(Gamer g) {
        int id = idGamers.incrementAndGet();
        this.gamers.put(id, g);
        return id;
    }

    public Map<Integer, Gamer> getGamers() {
        return gamers;
    }

    public Integer getTurn() {
        return turn;
    }

    public void nextTurn() {
        this.turn = this.gamers.higherKey(turn);
        if (this.turn == null) {
            this.turn = this.gamers.firstKey();
        }
    }

    public String getSecretWord() {
        return secretWord;
    }

    public char[] getUnderscoreFromWord() {
        return underscoreFromWord;
    }

    public boolean isComplete() {
        return complete;
    }

    //Gerena una palabra aleatoria del Array y llama al método transformar.
    public void generateWord() {
        Random r = new Random();
        int randomNumber = r.nextInt(WORDS.length);
        this.secretWord = WORDS[randomNumber];
        this.transformWords();
    }

    //Devolve las letras descubiertas
    public String getLettersDiscovered() {
        String lettersDiscovered = new String();
        for (int j = 0; j < this.underscoreFromWord.length; j++) {
            lettersDiscovered += this.underscoreFromWord[j];
        }
        return lettersDiscovered;
    }

    //Genera un Array de caracteres(guiones-bajo) equivalente a la palabra secreta generada.
    public void transformWords() {
        //inicializamos el Array al tamaño de la palabra secreta
        this.underscoreFromWord = new char[this.secretWord.length()];
        for (int i = 0; i < this.underscoreFromWord.length; i++) {
            this.underscoreFromWord[i] = '_';
        }
    }

    //El juego en si,devolve las letras descubiertas
    public String play(String word) {
        String finalWord = new String();
        //si es solo una letra
        if (word.length() == 1) {
            char letter = word.charAt(0);
            for (int i = 0; i < this.secretWord.length(); i++) {
                if (this.secretWord.charAt(i) == letter) //sustituye el caracter por la letra
                {
                    this.underscoreFromWord[i] = letter;
                }
            }
            //Cadena con las letras descubiertas
            finalWord = getLettersDiscovered();

            //si es más de una letra
        } else {
            //si adivina la palabra secreta,se genera otra.
            if (word.equalsIgnoreCase(this.secretWord)) {
                this.complete = true;
                finalWord = word;
                this.generateWord();
            } else {
                finalWord = getLettersDiscovered();

            }
        }

        return finalWord;
    }

}
