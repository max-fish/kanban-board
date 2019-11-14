package ui;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;



public class Card extends Application{
    @Override
    public void start(Stage primaryStage){
        HBox root = new HBox();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styling/scene_styling.css").toExternalForm());


        primaryStage.setTitle("Exercise14_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
