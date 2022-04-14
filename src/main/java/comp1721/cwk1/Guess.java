package comp1721.cwk1;

import java.util.Locale;
import java.util.Scanner;
import java.util.TreeSet;

import static java.awt.Color.BLACK;


public class Guess {
    // Use this to get player input in readFromPlayer()
    private static final Scanner INPUT = new Scanner(System.in);
    private static final int BLACK = 30;
    private static int numberOfGuesses;
    String word;

    // TODO: Implement constructor with int parameter
    public Guess(int numberOfGuesses) {
        if (numberOfGuesses <= 0 || numberOfGuesses > 6) {
            throw new GameException("Number of guesses must be between 1 and 6");
        }
        this.numberOfGuesses = numberOfGuesses;
        this.word = "";
    }

    // TODO: Implement constructor with int and String parameters
    public Guess(int numberOfGuesses, String word) {
        if (numberOfGuesses < 0 || numberOfGuesses > 6) {
            throw new GameException("Number of guesses must be between 0 and 6");
        }
        if (word.length() != 5) {
            throw new GameException("Word must be 5 characters long");
        }
        if (numberOfGuesses <= 0 || numberOfGuesses >= 6) {
            throw new GameException("Number of guesses must be between 1 and 6");
        }
        for(int i = 0; i < word.length(); i++) {
            if(!Character.isLetter(word.charAt(i))) {
                throw new GameException("Word must only contain letters");
            }
        }
        Guess.numberOfGuesses = numberOfGuesses;
        word = word.toUpperCase(Locale.ROOT);
        this.word = word;
    }

    // TODO: Implement getGuessNumber(), returning an int
    public int getGuessNumber() {
        return numberOfGuesses;
    }

    // TODO: Implement getChosenWord(), returning a String
    public String getChosenWord() {
        return word;
    }

    // TODO: Implement readFromPlayer()
    public void readFromPlayer() {
        System.out.println("Enter a word of 5 characters");
        word = INPUT.nextLine().toUpperCase(Locale.ROOT);
        for(int i = 0; i < word.length(); i++) {
            if(!Character.isLetter(word.charAt(i))) {
                throw new GameException("Word must only contain letters");
            }
        }
        if (word.length() != 5) {
            throw new GameException("Word must be 5 characters long");
        }
    }

    // TODO: Implement compareWith(), giving it a String parameter and String return type
    public String compareWith(String guessword) {
        String result[] = new String[5];
        TreeSet<Character> letters = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            letters.add(word.charAt(i));
        }
        if(guessword.length() != 5) {
            throw new GameException("Word must be 5 characters long");
        }
        for(int i = 0; i < 5; i++) {
            if(guessword.charAt(i) == word.charAt(i)) {
                result[i] = "\033["+BLACK+";102m " + guessword.charAt(i) + " \033[0m";
            } else if(letters.contains(guessword.charAt(i))) {
                result[i] = "\033["+BLACK +";103m " + guessword.charAt(i) + " \033[0m";
            } else {
                result[i] = "\033["+BLACK +";107m " + guessword.charAt(i) + " \033[0m";
            }
        }
        return result[0] + result[1] + result[2] + result[3] + result[4];
    }

    // TODO: Implement matches(), giving it a String parameter and boolean return type
    public boolean matches(String guess) {
        return this.word.equals(guess);
    }
}
