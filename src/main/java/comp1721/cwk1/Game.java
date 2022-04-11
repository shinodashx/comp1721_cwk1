package comp1721.cwk1;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Game {
    private final WordList wordList;
    private String word;
    private final List<String> words = new ArrayList<String>();

    // TODO: Implement constructor with String parameter
    public Game(String filename) throws FileNotFoundException {
        wordList = new WordList(filename);
        LocalDate date = LocalDate.of(2021, 6, 19);
        LocalDate today = LocalDate.now();
        int days = (int) date.toEpochDay() - (int) today.toEpochDay();
        this.word = wordList.getWord(days);
    }

    // TODO: Implement constructor with int and String parameters
    public Game(int idx, String filename) throws FileNotFoundException {
        wordList = new WordList(filename);
        this.word = wordList.getWord(idx);
    }

    // TODO: Implement play() method
    public void play() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
        }
    }

    // TODO: Implement save() method, with a String parameter

}
