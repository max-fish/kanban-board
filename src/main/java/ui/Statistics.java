package ui;

import com.jfoenix.controls.JFXDecorator;
import controllers.StatisticsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Statistics  {

    private StatisticsController controller;

    public Statistics() throws IOException { start(); }

      public void start() throws IOException {
        Stage stage = new Stage();
        System.setProperty("prism.lcdtext", "false"); //for better font rendering
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/layouts/statistics_ui.fxml"));
        Parent root = fxmlLoader.load();

        JFXDecorator jfxDecorator = new JFXDecorator(stage, root);
        jfxDecorator.setCustomMaximize(true);
        jfxDecorator.setTitle("Statistics");

        controller = fxmlLoader.getController();

        Scene scene = new Scene(jfxDecorator, 800, 400);
        scene.getStylesheets().add(getClass().getResource("/styling/scene_styling.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public StatisticsController getController(){
        return controller;
    }
}
