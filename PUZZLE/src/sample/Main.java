package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #ff931c");

        Label label = new Label("PUZZLE");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(1024,256);
        label.setFont(new Font(80));
        label.setTextFill(Color.web("#0076a3"));
        label.setStyle("-fx-background-color: #00ccff");
        vbox.getChildren().add(0,label);

        Button zacznijG = new Button("Start game");
        zacznijG.setAlignment(Pos.CENTER);
        zacznijG.setPrefSize(1024,256);
        zacznijG.setFont(new Font(80));
        zacznijG.setTextFill(Color.web("ff0000"));
        vbox.getChildren().add(1,zacznijG);

        zacznijG.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Game().newGame();
            }
        });

        Button wyniki = new Button("Scores");
        wyniki.setAlignment(Pos.CENTER);
        wyniki.setPrefSize(1024,256);
        wyniki.setFont(new Font(80));
        wyniki.setTextFill(Color.web("#666699"));
        vbox.getChildren().add(2,wyniki);

        wyniki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Score().score();
            }
        });

        primaryStage.setTitle("Puzzle");
        primaryStage.setScene(new Scene(vbox, 1024, 768));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
