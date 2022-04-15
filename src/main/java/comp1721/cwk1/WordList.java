package comp1721.cwk1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordList {
    String filename;
    ArrayList<String> wordsList;

    // TODO: Implement constructor with a String parameter
    WordList(String filename) throws FileNotFoundException{
        this.filename = filename;
        wordsList = new ArrayList<String>();
        readFile();
    }

    // TODO: Implement size() method, returning an int
    int size() {
        return wordsList.size();
    }

    // TODO: Implement getWord() with an int parameter, returning a String
    String getWord(int index) {
        if (index < 0 || index >= wordsList.size()) {
            throw new GameException("Index out of bounds");
        }
        return wordsList.get(index);
    }

    private void readFile() throws FileNotFoundException {
        try {
            Scanner sc = new Scanner(new java.io.File(filename));
            while (sc.hasNext()) {
                wordsList.add(sc.next());
            }
            wordsList.trimToSize();
            sc.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }
}
