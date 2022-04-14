package comp1721.cwk1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        int days = (int) today.toEpochDay() - (int) date.toEpochDay();
        //System.out.println(days);
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
        for (int i = 1; i < 7; i++) {
            Guess guess = new Guess(i);
            guess.readFromPlayer();
            if(guess.matches(this.word)) {
                words.add(this.word);
                System.out.println(guess.compareWith(this.word));
                return;
            } else {
                words.add(guess.compareWith(this.word));
                System.out.println(guess.compareWith(this.word));
            }
        }
    }

    // TODO: Implement save() method, with a String parameter
    public void save(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            for (String word : words) {
                fw.write(word + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
