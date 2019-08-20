package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Score {

    private Stage stage;
    private VBox vBox;
    private ArrayList<String> scrStr = new ArrayList<>();
    private String scr;
    private BufferedReader br;
    private Label text;
    private ArrayList<Label>  scrLab = new ArrayList<>();

    public void score(){
        vBox = new VBox();
        stage = new Stage();
        stage.setTitle("Score");
        downloadScore();
        stage.setScene(new Scene(vBox, 320, 775));
        stage.showAndWait();
    }

    public void downloadScore(){
       try {
           br = new BufferedReader(new FileReader("C:\\Projekt/wyniki.txt"));
           while ((scr = br.readLine()) != null){
               if (scrStr.size() < 17){
                   scrStr.add(scr);
                   System.out.println(scr);
               }else {
                   scrStr.remove(scrStr.size() - 1);
                   scrStr.add(0,scr);
               }
           }
       }catch (IOException io){
           io.printStackTrace();
       }
       addScore();
    }

    public void addScore(){
        for (String w:scrStr) {
            text = new Label();
            text.setFont(new Font(30));
            text.setTextFill(Color.web("#7800FF"));
            text.setText(w);
            scrLab.add(text);
        }

        Label tmp;
        for (int i = 0;i < scrLab.size();i++){
            tmp = scrLab.get(i);
            vBox.getChildren().add(i,tmp);
        }
    }
}
