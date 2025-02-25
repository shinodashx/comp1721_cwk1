package comp1721.cwk1;

import java.util.*;

import static java.awt.Color.BLACK;

import javafx.util.Pair;
import javafx.application.Application;


public class Guess {
    // Use this to get player input in readFromPlayer()
    private static final Scanner INPUT = new Scanner(System.in);
    private static final int BLACK = 30;
    private final int numberOfGuesses;
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
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                throw new GameException("Word must only contain letters");
            }
        }
        this.numberOfGuesses = numberOfGuesses;
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

        word = INPUT.nextLine().toUpperCase(Locale.ROOT);
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                throw new GameException("Word must only contain letters");
            }
        }
        if (word.length() != 5) {
            throw new GameException("Word must be 5 characters long");
        }
    }

    // TODO: Implement compareWith(), giving it a String parameter and String return type
    public String compareWith(String guessword) {
        if (guessword.length() != 5) {
            throw new GameException("Word must be 5 characters long");
        }
        String[] result = new String[5];
        TreeMap<Character, ArrayList> WordMap = new TreeMap<Character, ArrayList>();
        for (int i = 0; i < 5; i++) {
            if (WordMap.containsKey(guessword.charAt(i))) {
                ArrayList<Integer> temp = WordMap.get(guessword.charAt(i));
                ArrayList<Integer> temp2 = new ArrayList<>();
                temp2.add(temp.get(0) + 1);
                for (int j = 1; j < temp.size(); j++) {
                    temp2.add(temp.get(j));
                }
                temp2.add(i);
                WordMap.put(guessword.charAt(i), temp2);
            } else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(1);
                temp.add(i);
                WordMap.put(guessword.charAt(i), temp);
            }
        }
        for (int i = 0; i < 5; i++) {
            if (guessword.charAt(i) == word.charAt(i)) {
                ArrayList<Integer> temp = WordMap.get(guessword.charAt(i));
                ArrayList<Integer> temp2 = new ArrayList<Integer>();
                temp2.add(temp.get(0) - 1);
                for (int j = 1; j < temp.size(); j++) {
                    temp2.add(temp.get(j));
                }
                WordMap.put(guessword.charAt(i), temp2);
                result[i] = "\033[" + BLACK + ";102m " + word.charAt(i) + " \033[0m";
            }
        }
        for (int i = 0; i < 5; i++) {
            if (result[i] == null) {
                if (WordMap.containsKey(word.charAt(i))) {
                    ArrayList<Integer> temp = WordMap.get(word.charAt(i));
                    ArrayList<Integer> temp2 = new ArrayList<Integer>();
                    if (temp.get(0) > 0) {
                        temp2.add(temp.get(0) - 1);
                        for (int j = 1; j < temp.size(); j++) {
                            temp2.add(temp.get(j));
                        }
                        WordMap.put(word.charAt(i), temp2);
                        result[i] = "\033[" + BLACK + ";103m " + word.charAt(i) + " \033[0m";
                    } else {
                        result[i] = "\033[" + BLACK + ";107m " + word.charAt(i) + " \033[0m";
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (result[i] == null) {
                result[i] = "\033[" + BLACK + ";107m " + word.charAt(i) + " \033[0m";
            }
        }
        return result[0] + result[1] + result[2] + result[3] + result[4];
    }

    // Implement compareWithForGui(), giving it a String parameter and String return type
    public String compareWithForGui(String guessword) {
        if (guessword.length() != 5) {
            throw new GameException("Word must be 5 characters long");
        }
        String[] result = new String[5];
        TreeMap<Character, ArrayList> WordMap = new TreeMap<Character, ArrayList>();
        for (int i = 0; i < 5; i++) {
            if (WordMap.containsKey(guessword.charAt(i))) {
                ArrayList<Integer> temp = WordMap.get(guessword.charAt(i));
                ArrayList<Integer> temp2 = new ArrayList<>();
                temp2.add(temp.get(0) + 1);
                for (int j = 1; j < temp.size(); j++) {
                    temp2.add(temp.get(j));
                }
                temp2.add(i);
                WordMap.put(guessword.charAt(i), temp2);
            } else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(1);
                temp.add(i);
                WordMap.put(guessword.charAt(i), temp);
            }
        }
        for (int i = 0; i < 5; i++) {
            if (guessword.charAt(i) == word.charAt(i)) {
                ArrayList<Integer> temp = WordMap.get(guessword.charAt(i));
                ArrayList<Integer> temp2 = new ArrayList<Integer>();
                temp2.add(temp.get(0) - 1);
                for (int j = 1; j < temp.size(); j++) {
                    temp2.add(temp.get(j));
                }
                WordMap.put(guessword.charAt(i), temp2);
                result[i] = "Y";
            }
        }
        for (int i = 0; i < 5; i++) {
            if (result[i] == null) {
                if (WordMap.containsKey(word.charAt(i))) {
                    ArrayList<Integer> temp = WordMap.get(word.charAt(i));
                    ArrayList<Integer> temp2 = new ArrayList<Integer>();
                    if (temp.get(0) > 0) {
                        temp2.add(temp.get(0) - 1);
                        for (int j = 1; j < temp.size(); j++) {
                            temp2.add(temp.get(j));
                        }
                        WordMap.put(word.charAt(i), temp2);
                        result[i] = "T";
                    } else {
                        result[i] = "N";
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (result[i] == null) {
                result[i] = "N";
            }
        }
        return result[0] + result[1] + result[2] + result[3] + result[4];
    }

    // accessable person
    public void getWord(String guessword) {
        TreeSet<Integer> perfect = new TreeSet<Integer>();
        TreeSet<Integer> wrong = new TreeSet<Integer>();
        TreeSet<Integer> right = new TreeSet<Integer>();
        TreeMap<Integer, String> wordMap = new TreeMap<Integer, String>();
        wordMap.put(1, "1st");
        wordMap.put(2, "2nd");
        wordMap.put(3, "3rd");
        wordMap.put(4, "4th");
        wordMap.put(5, "5th");
        String result = compareWithForGui(guessword);
        for (int i = 0; i < 5; i++) {
            if (result.charAt(i) == 'Y') {
                perfect.add(i+1);
            } else if (result.charAt(i) == 'T') {
                right.add(i+1);
            } else {
                wrong.add(i+1);
            }
        }
        if (right.size() > 0) {
            if (right.size() == 1) {
                System.out.printf("%s correct but in wrong place", wordMap.get(right.first()));
            } else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for (int i : right) {
                    temp.add(i);
                }
                for (int i = 0; i < temp.size() - 1; i++) {
                    if(i!=temp.size()-2){
                        System.out.printf("%s, ", wordMap.get(temp.get(i)));
                    } else {
                        System.out.printf("%s ", wordMap.get(temp.get(i)));
                    }

                }
                System.out.printf("and %s correct but in wrong place", wordMap.get(temp.get(temp.size() - 1)));
            }
            if(perfect.size()==0){
                System.out.printf(".");
            } else {
                System.out.printf(", ");

            }
        }
        if (perfect.size() > 0) {
            if (perfect.size() == 1) {
                System.out.printf("%s perfect", wordMap.get(perfect.first()));
                System.out.printf(".");
            } else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for (int i : perfect) {
                    temp.add(i);
                }
                for (int i = 0; i < temp.size() - 1; i++) {
                    if(i!=temp.size()-2){
                        System.out.printf("%s, ", wordMap.get(temp.get(i)));
                    } else {
                        System.out.printf("%s ", wordMap.get(temp.get(i)));
                    }
                }
                System.out.printf("and %s perfect", wordMap.get(temp.get(temp.size() - 1)));
                System.out.printf(".");
            }
        }
        if(wrong.size() == 5) {
            System.out.printf("No one correct.");
        }
        System.out.printf("\n");

    }

    // TODO: Implement matches(), giving it a String parameter and boolean return type
    public boolean matches(String guess) {
        return this.word.equals(guess);
    }
}