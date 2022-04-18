package comp1721.cwk1;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.time.LocalDate;


public class javafxGui {

    private final static int MUL_FACTOR = 100;
    private final static int MAX_VALUE = 100;
    private final static int MIN_VALUE = 0;
    private final static int SPACING = 10;
    private final int ROWS;
    private final int COLS;
    private final static Stage stage = new Stage();
    private final Canvas canvas;
    private String nowword;
    public javafxGui() throws FileNotFoundException {
        this.ROWS = 1;
        this.COLS = 5;
        this.canvas = new Canvas(this.COLS * MUL_FACTOR + SPACING * (this.COLS + 1), this.ROWS * MUL_FACTOR + SPACING * (this.ROWS + 1));
        BorderPane root = getPane();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);


        //add key
        scene.setOnKeyPressed(event -> {
            if(Character.isLetter(event.getCharacter().charAt(0))) {
                if(nowword.length() < 5 || nowword.length() > 5) {
                    event.consume();
                    return;
                }
                nowword += event.getCharacter();
            }

        });

        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER && nowword.length() == 5) {
                try {
                    updateCanvas();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                nowword = nowword.substring(0, nowword.length() - 1);
                try {
                    updateCanvas();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            event.consume();
        });

    }

    public void start() {
        stage.show();
    }
    public static void main(String[] args) throws FileNotFoundException {
        javafxGui gui = new javafxGui();
        gui.start();
    }

    BorderPane getPane(){
        BorderPane root = new BorderPane();

        GridPane gridPane = new GridPane();

        BorderPane bottomPane = new BorderPane();
        GridPane centerPane = new GridPane();
        GridPane leftPane = new GridPane();
        GridPane rightPane = new GridPane();
        GridPane topPane = new GridPane();
        bottomPane.setCenter(centerPane);
        bottomPane.setLeft(leftPane);
        bottomPane.setRight(rightPane);
        bottomPane.setTop(topPane);

        drawGrid(canvas.getGraphicsContext2D(), this.COLS, this.ROWS);
        gridPane.add(canvas, 0, 0);
        gridPane.setPadding(new Insets(5*SPACING, 5*SPACING, 5*SPACING, 10*SPACING));

        root.setTop(gridPane);
        root.setCenter(bottomPane);
        return root;
    }
    void drawGrid(GraphicsContext graphicsContext2D, int cols, int rows) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                graphicsContext2D.setFill(Color.GRAY);
                graphicsContext2D.strokeRoundRect(i * MUL_FACTOR + SPACING * (i + 1), j * MUL_FACTOR + SPACING * (j + 1), MUL_FACTOR, MUL_FACTOR, 50, 50);
            }
        }
    }
    public String Game(String filename) throws FileNotFoundException {
        WordList wordList = new WordList(filename);
        LocalDate date = LocalDate.of(2021, 6, 19);
        LocalDate today = LocalDate.now();
        int days = (int) today.toEpochDay() - (int) date.toEpochDay();
        //System.out.println(days);
        return wordList.getWord(days);
    }

    void updateCanvas() throws FileNotFoundException {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawGrid(graphicsContext2D, this.COLS, this.ROWS);
        String theWord = Game("wordlist.txt");
        for(int i = 0 ;i<=6;++i) {
            Guess guess = new Guess(i);
            String word = guess.compareWithForGui(theWord);
            for (int j = 0; j < word.length(); j++) {
                Color color = Color.GRAY;
                if (word.charAt(j) == 'Y') {
                    color = Color.GREEN;
                } else if(word.charAt(j) == 'T') {
                    color= Color.YELLOW;
                }
                graphicsContext2D.setFill(color);
                graphicsContext2D.fillRoundRect(j * MUL_FACTOR + SPACING * (j + 1), i * MUL_FACTOR + SPACING * (i + 1), MUL_FACTOR, MUL_FACTOR, 50, 50);

                graphicsContext2D.setFill(Color.WHITE);
                graphicsContext2D.fillText(word.charAt(j) + "", j * MUL_FACTOR + SPACING * (j + 1) + MUL_FACTOR / 2, i * MUL_FACTOR + SPACING * (i + 1) + MUL_FACTOR / 2);
                graphicsContext2D.setFont(new Font(graphicsContext2D.getFont().getName(), MUL_FACTOR / 2));
            }
        }
        graphicsContext2D.setFill(Color.BLACK);
        int i = 0;
        for(char c : theWord.toCharArray()) {
            graphicsContext2D.setFont(new Font(graphicsContext2D.getFont().getName(), MUL_FACTOR / 2));
            graphicsContext2D.fillText(c + "", i * MUL_FACTOR + SPACING * (i + 1) + MUL_FACTOR / 2, 7 * MUL_FACTOR + SPACING * (7 + 1) + MUL_FACTOR / 2);
        }
    }


}
