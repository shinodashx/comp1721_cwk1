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
    private int round;
    private int winFlag;
    private final List<String> words = new ArrayList<String>();
    int acsFlag = 0;

    // TODO: Implement constructor with String parameter
    public Game(String filename) throws FileNotFoundException {
        round = 0;
        winFlag = 0;
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
            System.out.printf("Enter guess(%d/6) :",i);
            round++;
            Guess guess = new Guess(i);
            guess.readFromPlayer();
            if (guess.matches(this.word)) {
                if(acsFlag!=1)System.out.println(guess.compareWith(this.word));
                else {
                    guess.getWord(this.word);
                }
                if(i == 1){
                    System.out.println("Superb - Got it in one!");
                } else if(i<6){
                    System.out.println("Well done!");
                } else {
                    System.out.println("That was a close call!\n");
                }
                winFlag = 1;
                words.add(guess.compareWith(this.word));
                return;
            } else {
                words.add(guess.compareWith(this.word));
                if(acsFlag!=1)System.out.println(guess.compareWithForGui(this.word));
                else {
                    guess.getWord(this.word);
                }
            }
        }
    }

    // TODO: Implement save() method, with a String parameter
    public void save(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            fw.write(winFlag + "\n");
            fw.write(round + "\n");
            for (String word : words) {
                fw.write(word + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Implement saveHistory() method, with a String parameter
    public void saveHistory(String filename) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            for (String word : words) {
                fw.write(word + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getacs(int x){
        if(x == 1){
            acsFlag = 1;
        } else {
            acsFlag = 0;
        }
    }

}
