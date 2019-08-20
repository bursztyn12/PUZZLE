package sample;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Message {

    private String time;
    private Stage stage;
    private GridPane gridPane = new GridPane();

    Message(String t){
        time = t;
    }

    public void showMessage(){
        stage = new Stage();
        stage.setTitle("Message");
        Label end = new Label("You made it!!");
        end.setFont(new Font(30));
        gridPane.add(end,0,0);
        Label wiad = new Label("Your time : ");
        wiad.setFont(new Font(30));
        gridPane.add(wiad,0,1);
        Text text = new Text(time);
        text.setFont(new Font(30));
        gridPane.add(text,1,1);
        stage.setScene(new Scene(gridPane, 400, 100));
        stage.showAndWait();
    }
}
