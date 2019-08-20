package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Game {

    private GridPane board = new GridPane();
    private GridPane clock = new GridPane();
    private GridPane control = new GridPane();
    private VBox vBox = new VBox();
    private Text min;
    private Text sec;
    private Text hou;
    private Task task;
    private Thread thread;
    private Stage stage;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private Button finish;
    private ImageView imgVElementy[] = new ImageView[12];
    private Button b1 = new Button();
    private Button b2 = new Button();
    private Button b3 = new Button();
    private Button b4 = new Button();
    private Button b5 = new Button();
    private Button b6 = new Button();
    private Button b7 = new Button();
    private Button b8 = new Button();
    private Button b9 = new Button();
    private Button b10 = new Button();
    private Button b11 = new Button();
    private Button[] buttons = new Button[11];
    private int blankCol = 1, blankRow = 1, puzzleCol, puzzleRow;
    private ArrayList<Integer> numbers = new ArrayList<>();

    public void start(Stage stage) throws Exception {

    }

    public void newGame() {

        cut();

        clock.setPrefSize(1024, 128);

        Label godzina = new Label("Hours");
        godzina.setTextAlignment(TextAlignment.CENTER);
        godzina.setAlignment(Pos.CENTER);
        godzina.setPrefSize(341, 64);
        godzina.setFont(new Font(40));
        godzina.setTextFill(Color.web("#5c5c8a"));
        godzina.setStyle("-fx-background-color: #ffca18");
        clock.add(godzina, 0, 0);

        hou = new Text("00");
        hou.setTextAlignment(TextAlignment.RIGHT);
        hou.setFont(new Font(64));
        hou.setStyle("-fx-background-color: #ff0925");
        clock.add(hou, 0, 1);

        Label minute = new Label("Minutes");
        minute.setTextAlignment(TextAlignment.CENTER);
        minute.setAlignment(Pos.CENTER);
        minute.setPrefSize(341, 128);
        minute.setFont(new Font(40));
        minute.setTextFill(Color.web("#5c5c8a"));
        minute.setStyle("-fx-background-color: #ffca18");
        clock.add(minute, 1, 0);

        min = new Text("00");
        min.setTextAlignment(TextAlignment.RIGHT);
        min.setFont(new Font(64));
        min.setStyle("-fx-background-color: #ff0925");
        clock.add(min, 1, 1);

        Label second = new Label("Seconds");
        second.setTextAlignment(TextAlignment.CENTER);
        second.setAlignment(Pos.CENTER);
        second.setPrefSize(341, 64);
        second.setFont(new Font(40));
        second.setTextFill(Color.web("#5c5c8a"));
        second.setStyle("-fx-background-color: #ffca18");
        clock.add(second, 2, 0);

        sec = new Text("00");
        sec.setTextAlignment(TextAlignment.RIGHT);
        sec.setFont(new Font(64));
        sec.setStyle("-fx-background-color: #ff0925");
        clock.add(sec, 2, 1);

        vBox.getChildren().add(0, clock);

        board.setPrefSize(1024, 512);
        vBox.getChildren().add(1, board);

        b1 = new Button();
        b1.setPrefSize(341, 128);

        b2 = new Button();
        b2.setPrefSize(341, 128);

        b3 = new Button();
        b3.setPrefSize(341, 128);

        b4 = new Button();
        b4.setPrefSize(341, 128);

        b5 = new Button();
        b5.setPrefSize(341, 128);

        b6 = new Button();
        b6.setPrefSize(341, 128);

        b7 = new Button();
        b7.setPrefSize(341, 128);

        b8 = new Button();
        b8.setPrefSize(341, 128);

        b9 = new Button();
        b9.setPrefSize(341, 128);

        b10 = new Button();
        b10.setPrefSize(341, 128);

        b11 = new Button();
        b11.setPrefSize(341, 128);

        finish = new Button("Finish");
        finish.setPrefSize(1024, 128);
        finish.setAlignment(Pos.TOP_CENTER);
        finish.setFont(new Font(40));
        finish.setTextFill(Color.web("#ff0000"));
        control.add(finish, 1, 0);

        drawElements();

        vBox.getChildren().add(2, control);

        moveButton(b1);
        moveButton(b2);
        moveButton(b3);
        moveButton(b4);
        moveButton(b5);
        moveButton(b6);
        moveButton(b7);
        moveButton(b8);
        moveButton(b9);
        moveButton(b10);
        moveButton(b11);

        stage = new Stage();
        stage.setTitle("Game");
        stage.setScene(new Scene(vBox, 1024, 788));
        time();
        stage.showAndWait();
    }

    public void moveButton(Button b){
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (GridPane.getColumnIndex(b) + 1 == blankCol && GridPane.getRowIndex(b) == blankRow ||
                        GridPane.getRowIndex(b) + 1 == blankRow && GridPane.getColumnIndex(b) == blankCol ||
                        GridPane.getColumnIndex(b) - 1 == blankCol && GridPane.getRowIndex(b) == blankRow ||
                        GridPane.getRowIndex(b) - 1 == blankRow && GridPane.getColumnIndex(b) == blankCol) {

                    puzzleCol = GridPane.getColumnIndex(b);
                    puzzleRow = GridPane.getRowIndex(b);

                    board.getChildren().remove(b);
                    board.add(b, blankCol, blankRow);

                    blankCol = puzzleCol;
                    blankRow = puzzleRow;
                }

                check();
            }
        });
    }

    public void time() {
        task = new Task() {
            @Override
            public Void call() throws Exception {
                String zeroS = "0";
                String zeroM = "0";
                String zeroG = "0";
                String sS = "";
                String sM = "";
                String sG = "";
                while (true) {

                    ++seconds;

                    System.out.println("H : " + hours + " M :" + minutes + " S : " + seconds);

                    if (seconds <= 60) {
                        if (seconds < 10) {
                            zeroS += seconds;
                            sec.setText(zeroS);
                            zeroS = resetZero(zeroS);
                        } else {
                            sS += seconds;
                            sec.setText(sS);
                            sS = reset(sS);
                        }
                    } else {
                        seconds = 0;
                        ++minutes;
                        if (minutes <= 60) {
                            if (minutes < 10) {
                                zeroM += minutes;
                                min.setText(zeroM);
                                zeroM = resetZero(zeroM);
                            } else {
                                sM += minutes;
                                min.setText(sM);
                                sM = reset(sM);
                            }
                        } else {
                            minutes = 0;
                            ++hours;
                            if (hours <= 24) {
                                if (hours < 10) {
                                    zeroG += hours;
                                    hou.setText(zeroG);
                                    zeroG = resetZero(zeroG);
                                } else {
                                    sG += hours;
                                    hou.setText(sG);
                                    sG = reset(sG);
                                }
                            } else {
                                hours = 0;
                            }
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                return null;
            }
        };

        finish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                task.cancel();
                stage.close();
            }
        });

        thread = new Thread(task);
        thread.start();
    }

    public String reset(String sX) {
        sX = "";
        return sX;
    }

    public String resetZero(String zeroX) {
        zeroX = "0";
        return zeroX;
    }

    public void saveScore() {

        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter("C:\\Projekt\\wyniki.txt", true));
            String s = toString();
            System.out.println(s);
            bw.append(s);
            bw.newLine();
            bw.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void cut() {
        try {
            int colums = 3;
            int rows = 4;

            BufferedImage bufferedImage1 = ImageIO.read(new URL("https://karmytrovet.pl/uploads/blog/zywienie-dostosowane-do-wieku-psow.jpg"));

            //"C:\\Projekt2/jiro.jpg"

            int imageW = bufferedImage1.getWidth() / colums;
            System.out.println(imageW);
            int imageH = bufferedImage1.getHeight() / rows;
            System.out.println(imageH);

            int x = 0;
            int y = 0;

            for (int i = 0; i < rows; i++) {
                System.out.println("w petli");
                x = 0;
                for (int j = 0; j < colums; j++) {

                    BufferedImage bufferedImage2 = bufferedImage1.getSubimage(x, y, imageW, imageH);
                    File file = new File("C:\\Projekt/jiro" + i + j + ".jpg");
                    ImageIO.write(bufferedImage2, "jpg", file);

                    x += imageW;
                }
                y += imageH;
            }
            System.out.println("Obraz zostal pociety");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void drawElements() {

        putElementsInArray();

        Image image;
        ImageView imageView;
        int x = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                image = new Image("file:///C:/Projekt/jiro" + i + j + ".jpg");
                imageView = new ImageView(image);
                imgVElementy[x] = imageView;
                ++x;
            }
        }

        imgVElementy = deleteElement(imgVElementy);

        for (int i = 0; i < 11; i++) {
            buttons[i].setGraphic(imgVElementy[i]);
            numbers.add(i);
        }

        int elmnt;
        int elmt2;
        int size = numbers.size();

        System.out.println("LOSOWANIE");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 1 && j == 1){

                }else {
                    elmnt = (int) (Math.random() * size);
                    elmt2 = numbers.get(elmnt);
                    numbers.remove(elmnt);
                    size = numbers.size();
                    board.add(buttons[elmt2], i, j);
                    System.out.println(buttons[elmt2].getClass());
                }
            }
        }
    }

    public void check(){
        
        int b1Col = GridPane.getColumnIndex(b1);
        int b1Row = GridPane.getRowIndex(b1);

        int b2Col = GridPane.getColumnIndex(b2);
        int b2Row = GridPane.getRowIndex(b2);

        int b3Col = GridPane.getColumnIndex(b3);
        int b3Row = GridPane.getRowIndex(b3);

        int b4Col = GridPane.getColumnIndex(b4);
        int b4Row = GridPane.getRowIndex(b4);

        int b5Col = GridPane.getColumnIndex(b5);
        int b5Row = GridPane.getRowIndex(b5);

        int b6Col = GridPane.getColumnIndex(b6);
        int b6Row = GridPane.getRowIndex(b6);

        int b7Col = GridPane.getColumnIndex(b7);
        int b7Row = GridPane.getRowIndex(b7);

        int b8Col = GridPane.getColumnIndex(b8);
        int b8Row = GridPane.getRowIndex(b8);

        int b9Col = GridPane.getColumnIndex(b9);
        int b9Row = GridPane.getRowIndex(b9);

        int b10Col = GridPane.getColumnIndex(b10);
        int b10Row = GridPane.getRowIndex(b10);

        int b11Col = GridPane.getColumnIndex(b11);
        int b11Row = GridPane.getRowIndex(b11);

        if ((b1Col == 0 && b1Row == 0) && ((b2Col == 1 && b2Row == 0)) && (b3Col == 2 && b3Row == 0) &&
                (b4Col == 0 && b4Row == 1) && (b5Col == 1 && b5Row == 1) && (b6Col == 2 && b6Row == 1) &&
                (b7Col == 0 && b7Row == 2) && (b8Col == 1 && b8Row == 2) && (b9Col == 2 && b9Row == 2) &&
                (b10Col == 0 && b10Row == 3) && (b11Col == 1 && b11Row == 3)) {

            task.cancel();
            saveScore();
            new Message(toString()).showMessage();
            stage.close();
        }

    }

    public ImageView[] deleteElement(ImageView[] tab) {
        ImageView[] imageView = new ImageView[11];
        for (int i = 0; i < imageView.length; i++) {
            imageView[i] = tab[i];
        }
        return imageView;
    }

    public void putElementsInArray() {
        buttons[0] = b1;
        buttons[1] = b2;
        buttons[2] = b3;
        buttons[3] = b4;
        buttons[4] = b5;
        buttons[5] = b6;
        buttons[6] = b7;
        buttons[7] = b8;
        buttons[8] = b9;
        buttons[9] = b10;
        buttons[10] = b11;
    }

    public String toString() {
        return "H : " + hours + " M : " + minutes + " S : " + seconds;
    }
}

