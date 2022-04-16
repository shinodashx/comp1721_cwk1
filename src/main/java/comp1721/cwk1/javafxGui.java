package comp1721.cwk1;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class javafxGui {

    private final static int MUL_FACTOR = 100;
    private final static int MAX_VALUE = 100;
    private final static int MIN_VALUE = 0;
    private final static int SPACING = 10;
    private final Game game;
    private final int ROWS;
    private final int COLS;
    private final static Stage stage = new Stage();
    private final Canvas canvas;
    private String nowword;
    public javafxGui() throws FileNotFoundException {
        this.game = new Game("data/words.txt");
        this.ROWS = 1;
        this.COLS = 5;
        this.canvas = new Canvas(this.COLS * MUL_FACTOR + SPACING * (this.COLS + 1), this.ROWS * MUL_FACTOR + SPACING * (this.ROWS + 1));
        BorderPane root = getPane();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
    }
//    private void drawGrid(GraphicsContext graphicsContext2D, int cols, int rows) {
//        for(int i = 0; i < cols; i++) {
//            for(int j = 0; j < rows; j++) {
//                graphicsContext2D.setFill(Color.GRAY);
//                graphicsContext2D.strokeRoundRect(i * MUL_FACTOR + SPACING * (i + 1), j * MUL_FACTOR + SPACING * (j + 1), MUL_FACTOR, MUL_FACTOR, 50, 50);
//            }
//        }
//    }
    public void start() {
        stage.show();
    }
    public static void main(String[] args) throws FileNotFoundException {
        javafxGui gui = new javafxGui();
        gui.start();
    }
//    void updateCanvas(Canvas canvas) {
//        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
//        graphicsContext2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawGrid(graphicsContext2D, this.COLS, this.ROWS);
//        for(int i = 0; i < this.COLS; i++) {
//            for(int j = 0; j < this.ROWS; j++) {
//                graphicsContext2D.setFill(Color.BLACK);
//                graphicsContext2D.fillText(game.getGrid()[i][j].toString(), i * MUL_FACTOR + SPACING * (i + 1), j * MUL_FACTOR + SPACING * (j + 1));
//            }
//        }
//    }
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



}
